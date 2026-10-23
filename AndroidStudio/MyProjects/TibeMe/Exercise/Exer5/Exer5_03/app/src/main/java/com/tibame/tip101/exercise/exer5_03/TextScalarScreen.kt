package com.tibame.tip101.exercise.exer5_03

import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextScalarScreen() {
    var zoomAmount by remember { mutableFloatStateOf(1f) }
    // 初始字型尺寸為50
    val initialFontSize = 50
    var fontSizeText by remember { mutableStateOf("$initialFontSize") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Hello",
            fontSize = (initialFontSize * zoomAmount).sp,
            modifier = Modifier
                .graphicsLayer {
                    scaleX = zoomAmount
                    scaleY = zoomAmount
                }
                .transformable(
                    state = rememberTransformableState { zoomChange, panChange, rotationChange ->
                        zoomAmount *= zoomChange
                        fontSizeText = "${(initialFontSize * zoomAmount).toInt()}"
                    }
                ),
        )

        Text(text = fontSizeText, modifier = Modifier.padding(8.dp))
    }
}