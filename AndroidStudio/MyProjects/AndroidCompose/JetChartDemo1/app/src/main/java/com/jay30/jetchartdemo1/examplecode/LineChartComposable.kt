package com.jay30.jetchartdemo1.examplecode

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.unit.dp
import io.jetchart.common.animation.fadeInAnimation
import io.jetchart.line.Line
import io.jetchart.line.LineChart
import io.jetchart.line.renderer.line.SolidLineDrawer
import io.jetchart.line.renderer.point.FilledPointDrawer
import io.jetchart.line.renderer.xaxis.LineXAxisDrawer
import io.jetchart.line.renderer.yaxis.LineYAxisWithValueDrawer
import io.jetchart.line.Point
import kotlin.random.Random

@Composable
fun LineChartComposable() {
    LineChart(lines = listOf(
        Line(points = points(10), lineDrawer = SolidLineDrawer(thickness = 8.dp, color = Blue)),
        Line(points = points(15), lineDrawer = SolidLineDrawer(thickness = 8.dp, color = Red))
    ),
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .width(1000.dp)
            .height(500.dp),
        animation = fadeInAnimation(3000),
        pointDrawer = FilledPointDrawer(),
        xAxisDrawer = LineXAxisDrawer(),
        yAxisDrawer = LineYAxisWithValueDrawer(),
        horizontalOffsetPercentage = 1f,
    )
}

@Composable
private fun points(count: Int) = (1..count).map { Point(Random.nextFloat(), "Point$it") }