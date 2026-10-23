package com.jay30.animatedcontenttransitionscopedemo1

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntSize

enum class CartState {
    Expanded,
    Collapsed
}

@Composable
fun MainFrame() {
    val transitionSpec: AnimatedContentTransitionScope<CartState>.() -> ContentTransform = {
        // Fade in with a delay so that it starts after fade out
        fadeIn(animationSpec = tween(150, delayMillis = 150))
            .togetherWith(fadeOut(animationSpec = tween(150)))
            .using(
                SizeTransform { initialSize, targetSize ->
                    // Using different SizeTransform for different state change
                    if (CartState.Collapsed isTransitioningTo CartState.Expanded) {
                        keyframes {
                            durationMillis = 500
                            // Animate to full target width and by 200px in height at 150ms
                            IntSize(targetSize.width, initialSize.height + 200) at 150
                        }
                    } else {
                        keyframes {
                            durationMillis = 500
                            // Animate 1/2 the height without changing the width at 150ms.
                            // The width and rest of the height will be animated in the
                            // timeframe between 150ms and duration (i.e. 500ms)
                            IntSize(
                                initialSize.width,
                                (initialSize.height + targetSize.height) / 2
                            ) at 150
                        }
                    }
                }
            )
    }

    LazyColumn {
        item{
            Text(stringResource(R.string.text1))
        }
    }
}