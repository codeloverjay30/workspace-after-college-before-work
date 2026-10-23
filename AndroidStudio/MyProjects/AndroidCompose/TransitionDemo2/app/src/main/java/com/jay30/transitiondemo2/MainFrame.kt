package com.jay30.transitiondemo2

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextMotion

@Composable
fun MainFrame(){
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val scale by
    infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 8f,
        animationSpec = infiniteRepeatable(tween(1000), RepeatMode.Restart), label = ""
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            Text(
                text = "Hello",
                modifier =
                Modifier.graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    transformOrigin = TransformOrigin.Center
                },
                // Text composable does not take TextMotion as a parameter.
                // Provide it via style argument but make sure that we are copying from current theme
                style = LocalTextStyle.current.copy(textMotion = TextMotion.Animated)
            )
        }
    }

}