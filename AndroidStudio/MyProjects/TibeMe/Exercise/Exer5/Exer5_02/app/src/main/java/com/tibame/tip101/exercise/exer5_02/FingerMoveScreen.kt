package com.tibame.tip101.exercise.exer5_02

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
fun FingerMoveScreen(){
    var panAmount by remember { mutableStateOf(Offset.Zero) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Canvas(
            modifier = Modifier
                .size(20.dp)
                .graphicsLayer {
                    translationX = panAmount.x
                    translationY = panAmount.y
                }.transformable(
                    state = rememberTransformableState { zoomChange, panChange, rotationChange ->
                    panAmount += panChange
                },
            )
        ){
            val canvasQuadrantSize = size / 2F
            drawRect(
                color = Color.Blue,
                size = canvasQuadrantSize,
            )
        }
    }
}