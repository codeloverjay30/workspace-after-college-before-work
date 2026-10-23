package com.jay30.piechartdemo2

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class ConvexStyle(
    val blur: Dp = 5.dp,
    val offset: Dp = 4.dp,
    val glareColor: Color = Color.White.copy(0.48f),
    val shadowColor: Color = Color.Black.copy(0.48f)
)
