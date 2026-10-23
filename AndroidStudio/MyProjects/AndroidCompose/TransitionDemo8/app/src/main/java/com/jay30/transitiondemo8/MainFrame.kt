package com.jay30.transitiondemo8

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

import androidx.compose.material.Card
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource

@Composable
fun MainFrame() {
    // Creates a transition state with an initial state where visible = false
    val visibleState = remember { MutableTransitionState(false) }
    // Sets the target state of the transition state to true. As it's different than the initial
    // state, a transition from not visible to visible will be triggered.
    visibleState.targetState = true

    // Creates a transition with the transition state created above.
    val transition = rememberTransition(visibleState)
    // Adds a scale animation to the transition to scale the card up when transitioning in.
    val scale by
    transition.animateFloat(
        // Uses a custom spring for the transition.
        transitionSpec = { spring(dampingRatio = Spring.DampingRatioMediumBouncy) }, label = ""
    ) { visible ->
        if (visible) 1f else 0.8f
    }
    // Adds an elevation animation that animates the dp value of the animation.
    val elevation by
    transition.animateDp(
        // Uses a tween animation
        transitionSpec = {
            // Uses different animations for when animating from visible to not visible, and
            // the other way around
            if (false isTransitioningTo true) {
                tween(1000)
            } else {
                spring()
            }
        }, label = ""
    ) { visible ->
        if (visible) 10.dp else 0.dp
    }

    // Use `androidx.compose.material.Card`.
    // Don't use `androidx.compose.material3.Card`
    // since its syntax is different than `androidx.compose.material.Card`.
    Card(
        modifier = Modifier
            .graphicsLayer(scaleX = scale, scaleY = scale)
            .size(200.dp, 100.dp)
            .fillMaxWidth()
            .background(color = Color.Blue),
        elevation = elevation
    ) {
        Text(stringResource(R.string.text1))
    }
}