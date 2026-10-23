package com.jay30.inkgeometrydemo1

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.UiThread
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.ink.authoring.InProgressStrokeId
import androidx.ink.authoring.InProgressStrokesFinishedListener
import androidx.ink.authoring.InProgressStrokesView
import androidx.ink.brush.Brush
import androidx.ink.brush.StockBrushes
import androidx.ink.geometry.ImmutableBox
import androidx.ink.geometry.Vec
import androidx.ink.strokes.Stroke
import androidx.input.motionprediction.MotionEventPredictor
import com.jay30.inkgeometrydemo1.ui.theme.InkGeometryDemo1Theme

class MainActivity : ComponentActivity(), InProgressStrokesFinishedListener {
    private lateinit var inProgressStrokesView: InProgressStrokesView
    private lateinit var context: Context
    private var finishedStrokesState = mutableStateOf(emptySet<Stroke>())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inProgressStrokesView = InProgressStrokesView(this)
        enableEdgeToEdge()
        setContent {
            InkGeometryDemo1Theme {
                context = LocalContext.current
                DrawingSurface(
                    inProgressStrokesView = inProgressStrokesView
                )
            }
        }
        inProgressStrokesView.addFinishedStrokesListener(this)
    }

    @UiThread
    override fun onStrokesFinished(strokes: Map<InProgressStrokeId, Stroke>) {
        Toast.makeText(
            context,
            context.getString(R.string.onstrokesfinished_called),
            Toast.LENGTH_SHORT
        ).show()
        finishedStrokesState.value += strokes.values

        val MAX_CONTINUOUS_STROKES = 2
        if (finishedStrokesState.value.size >= MAX_CONTINUOUS_STROKES) {
            Toast.makeText(
                context,
                context.getString(R.string.reach_max_strokes, MAX_CONTINUOUS_STROKES),
                Toast.LENGTH_LONG
            ).show()
            val immutableBox = ImmutableBox.fromCenterAndDimensions(
                center = Vec.ORIGIN,
                width = 800.0f / 2,
                height = 400.0f / 2,
            )
            eraseWholeStrokes(
                eraserBox = immutableBox,
                finishedStrokesState = finishedStrokesState
            )
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Composable
    fun DrawingSurface(
        inProgressStrokesView: InProgressStrokesView
    ) {
        val currentPointerId = remember { mutableStateOf<Int?>(null) }
        val currentStrokeId = remember { mutableStateOf<InProgressStrokeId?>(null) }
        val defaultBrush = Brush.createWithColorIntArgb(
            family = StockBrushes.pressurePenLatest,
            colorIntArgb = Color.Black.toArgb(),
            size = 5F,
            epsilon = 0.1F
        )

        Box(
            modifier = Modifier
                .size(800.dp, 400.dp)
                .border(width = 3.dp, color = Color.Red),
        ) {
            AndroidView(
                modifier = Modifier
                    .matchParentSize()
                    .border(width = 3.dp, color = Color.Blue),
                factory = { context ->
                    val rootView = FrameLayout(context)
                    inProgressStrokesView.apply {
                        layoutParams =
                            FrameLayout.LayoutParams(
                                FrameLayout.LayoutParams.MATCH_PARENT,
                                FrameLayout.LayoutParams.MATCH_PARENT,
                            )
                    }
                    val predictor = MotionEventPredictor.newInstance(rootView)
                    val touchListener =
                        View.OnTouchListener { view, event ->
                            predictor.record(event)
                            val predictedEvent = predictor.predict()

                            try {
                                when (event.actionMasked) {
                                    MotionEvent.ACTION_DOWN -> {
                                        // First pointer - treat it as inking.
                                        view.requestUnbufferedDispatch(event)
                                        val pointerIndex = event.actionIndex
                                        val pointerId = event.getPointerId(pointerIndex)
                                        currentPointerId.value = pointerId
                                        currentStrokeId.value =
                                            inProgressStrokesView.startStroke(
                                                event = event,
                                                pointerId = pointerId,
                                                brush = defaultBrush
                                            )
                                        true
                                    }

                                    MotionEvent.ACTION_MOVE -> {
                                        val pointerId = checkNotNull(currentPointerId.value)
                                        val strokeId = checkNotNull(currentStrokeId.value)

                                        for (pointerIndex in 0 until event.pointerCount) {
                                            if (event.getPointerId(pointerIndex) != pointerId) continue
                                            inProgressStrokesView.addToStroke(
                                                event,
                                                pointerId,
                                                strokeId,
                                                predictedEvent
                                            )
                                        }
                                        true
                                    }

                                    MotionEvent.ACTION_UP -> {
                                        val pointerIndex = event.actionIndex
                                        val pointerId = event.getPointerId(pointerIndex)
                                        check(pointerId == currentPointerId.value)
                                        val currentStrokeId =
                                            checkNotNull(currentStrokeId.value)
                                        inProgressStrokesView.finishStroke(
                                            event,
                                            pointerId,
                                            currentStrokeId
                                        )
                                        view.performClick()
                                        true
                                    }

                                    MotionEvent.ACTION_CANCEL -> {
                                        val pointerIndex = event.actionIndex
                                        val pointerId = event.getPointerId(pointerIndex)
                                        check(pointerId == currentPointerId.value)

                                        val currentStrokeId =
                                            checkNotNull(currentStrokeId.value)
                                        inProgressStrokesView.cancelStroke(
                                            currentStrokeId,
                                            event
                                        )
                                        true
                                    }

                                    else -> false
                                }
                            } finally {
                                predictedEvent?.recycle()
                            }

                        }
                    rootView.setOnTouchListener(touchListener)
                    rootView.addView(inProgressStrokesView)
                    rootView
                },
            ) {

            }
        }
    }
}
