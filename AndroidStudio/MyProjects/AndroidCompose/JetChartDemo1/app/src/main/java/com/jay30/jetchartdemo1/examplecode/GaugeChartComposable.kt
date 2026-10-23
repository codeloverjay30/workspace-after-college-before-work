package com.jay30.jetchartdemo1.examplecode

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import io.jetchart.common.animation.fadeInAnimation
import io.jetchart.gauge.GaugeArcDrawer
import io.jetchart.gauge.GaugeChart
import io.jetchart.gauge.NeedleDrawer

@Composable
fun GaugeChartComposable() {
    GaugeChart(
        percentValue = 72f, //between 0 and 100
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        animation = fadeInAnimation(4000),
        pointerDrawer = NeedleDrawer(needleColor = Color.Blue, baseSize = 12.dp),
        arcDrawer = GaugeArcDrawer(thickness = 32.dp, cap = StrokeCap.Round)
    )
}
