package com.jay30.sharedtransitionlayoutdemo1

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MainFrame() {
    val infiniteTransition = rememberInfiniteTransition(label = "")

    val scale1 =
        infiniteTransition.animateFloat(
            5f,
            2f,
            // No offset for the 1st animation
            infiniteRepeatable(tween(600), RepeatMode.Reverse), label = ""
        )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            SharedTransitionLayout(
                modifier = Modifier.graphicsLayer {
                    scaleX = scale1.value
                    scaleY = scale1.value
                },
                content = {
                    Text(text = stringResource(R.string.text1))
                }
            )
        }

        item {
            SharedTransitionLayout(
                modifier = Modifier.graphicsLayer {
                    scaleX = scale1.value
                    scaleY = scale1.value
                },
                content = {
                    Text(text = stringResource(R.string.text2))
                }
            )
        }
    }
}