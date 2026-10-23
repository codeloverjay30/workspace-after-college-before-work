package com.jay30.animatedvisibilitydemo1

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainFrame() {
    val visible by remember { mutableStateOf(true) }
    AnimatedVisibility(
        visible = visible,
        enter =
        slideInHorizontally(animationSpec = tween(durationMillis = 200)) { fullWidth ->
            // Offsets the content by 1/3 of its width to the left, and slide towards right
            // Overwrites the default animation with tween for this slide animation.
            -fullWidth / 3
        } +
                fadeIn(
// Overwrites the default animation with tween
                    animationSpec = tween(durationMillis = 200)
                ),
        exit =
        slideOutHorizontally(animationSpec = spring(stiffness = Spring.StiffnessHigh)) {
            // Overwrites the ending position of the slide-out to 200 (pixels) to the right
            200
        } + fadeOut()
    ) {
        // Content that needs to appear/disappear goes here:
        Box(
            Modifier
                .fillMaxWidth()
                .requiredHeight(200.dp)
        ) {}
    }
}