package com.jay30.drawingviewdemo1

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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.ink.authoring.InProgressStrokeId
import androidx.ink.authoring.InProgressStrokesFinishedListener
import androidx.ink.authoring.InProgressStrokesView
import androidx.ink.brush.Brush
import androidx.ink.brush.StockBrushes
import androidx.ink.rendering.android.canvas.CanvasStrokeRenderer
import androidx.ink.strokes.Stroke
import androidx.input.motionprediction.MotionEventPredictor
import com.jay30.drawingviewdemo1.ui.theme.DrawingViewDemo1Theme

class MainActivity : ComponentActivity(), InProgressStrokesFinishedListener {
    private lateinit var inProgressStrokesView: InProgressStrokesView
    private lateinit var context: Context
    private lateinit var canvasStrokeRenderer: CanvasStrokeRenderer
    private val finishedStrokesState = mutableStateOf(emptySet<Stroke>())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inProgressStrokesView = InProgressStrokesView(this)
        enableEdgeToEdge()
        setContent {
            DrawingViewDemo1Theme {
                inProgressStrokesView = InProgressStrokesView(this)
                context = LocalContext.current
                inProgressStrokesView.addFinishedStrokesListener(this)

                DrawingSurface(
                    inProgressStrokesView = inProgressStrokesView,
                )
            }
        }
    }

    @UiThread
    override fun onStrokesFinished(strokes: Map<InProgressStrokeId, Stroke>) {
        Toast.makeText(
            context,
            context.getString(R.string.onstrokesfinished_called),
            Toast.LENGTH_SHORT
        ).show()
        finishedStrokesState.value += strokes.values
        inProgressStrokesView.removeFinishedStrokes(strokes.keys)
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

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
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
                                    val currentStrokeId = checkNotNull(currentStrokeId.value)
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

                                    val currentStrokeId = checkNotNull(currentStrokeId.value)
                                    inProgressStrokesView.cancelStroke(currentStrokeId, event)
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