package com.jay30.jetchartdemo1.examplecode

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.unit.dp
import io.jetchart.common.animation.fadeInAnimation
import io.jetchart.pie.PieChart
import io.jetchart.pie.Pies
import io.jetchart.pie.Slice
import io.jetchart.pie.renderer.FilledSliceDrawer

@Composable
fun PieChartComposable() {
    PieChart(pies = Pies(listOf(Slice(35f, Red), Slice(45f, Blue), Slice(15f, Yellow), Slice(5f, Cyan))),
        modifier = Modifier.height(340.dp),
        animation = fadeInAnimation(4000),
        sliceDrawer = FilledSliceDrawer(thickness = 60f)
    )
}