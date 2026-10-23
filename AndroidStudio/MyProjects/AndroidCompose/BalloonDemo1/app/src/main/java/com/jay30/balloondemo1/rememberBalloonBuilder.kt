package com.jay30.balloondemo1

import androidx.compose.runtime.Composable
import com.skydoves.balloon.ArrowPositionRules
import com.skydoves.balloon.Balloon
import com.skydoves.balloon.BalloonAnimation
import com.skydoves.balloon.BalloonHighlightAnimation
import com.skydoves.balloon.BalloonSizeSpec
import com.skydoves.balloon.compose.rememberBalloonBuilder

val builder: Balloon.Builder
    @Composable
    get() = rememberBalloonBuilder {
        setArrowSize(10)
        setArrowPositionRules(ArrowPositionRules.ALIGN_ANCHOR)
        setArrowPosition(0.5f)
        setWidth(BalloonSizeSpec.WRAP)
        setHeight(BalloonSizeSpec.WRAP)
        setPadding(12)
        setCornerRadius(8f)
        setBackgroundColorResource(R.color.teal_200)
        setBalloonAnimation(BalloonAnimation.ELASTIC)
        setIsVisibleOverlay(true)
        setOverlayColorResource(R.color.purple_500)
        setBalloonHighlightAnimation(BalloonHighlightAnimation.SHAKE)
        setDismissWhenClicked(true)
    }