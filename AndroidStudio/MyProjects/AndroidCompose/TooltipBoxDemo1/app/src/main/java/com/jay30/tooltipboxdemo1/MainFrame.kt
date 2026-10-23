package com.jay30.tooltipboxdemo1

import android.widget.Toast
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.window.PopupPositionProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainFrame() {
    val context = LocalContext.current

    val mutableTransitionState = remember { MutableTransitionState(false) }

    var shouldShowPlainTooltipBox by remember { mutableStateOf(false) }

    val popupPositionProvider = object : PopupPositionProvider {
        override fun calculatePosition(
            anchorBounds: IntRect,
            windowSize: IntSize,
            layoutDirection: LayoutDirection,
            popupContentSize: IntSize
        ): IntOffset {
            return IntOffset(
                x = 0,
                y = 0
            )
        }
    }

    val tooltipState = object : TooltipState {
        override val isPersistent: Boolean
            get() = true
        override val isVisible: Boolean
            get() = true
        override val transition: MutableTransitionState<Boolean>
            get() = mutableTransitionState

        override fun dismiss() {
            Toast.makeText(
                context,
                context.getString(R.string.tooltip_box_ondismiss_event_called),
                Toast.LENGTH_SHORT
            ).show()
        }

        override fun onDispose() {
            Toast.makeText(
                context,
                context.getString(R.string.tooltip_box_ondispose_event_called),
                Toast.LENGTH_SHORT
            ).show()
        }

        override suspend fun show(mutatePriority: MutatePriority) {
            Toast.makeText(
                context,
                context.getString(R.string.tooltip_box_show_function_called),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        item {
            TextButton(
                onClick = {
                    shouldShowPlainTooltipBox = !shouldShowPlainTooltipBox
                }
            ) {
                Text(stringResource(R.string.text1))
            }
        }

        item{
            if (!shouldShowPlainTooltipBox) return@item

            Toast.makeText(
                context,
                context.getString(R.string.should_show_tooltip_box),
                Toast.LENGTH_SHORT
            ).show()

            TooltipBox(
                positionProvider = popupPositionProvider,
                tooltip = {},
                state = tooltipState,
                modifier = Modifier,
                focusable = true,
                enableUserInput = false,
                content = {
                    Text(stringResource(R.string.text1))
                }
            )
        }
    }
}