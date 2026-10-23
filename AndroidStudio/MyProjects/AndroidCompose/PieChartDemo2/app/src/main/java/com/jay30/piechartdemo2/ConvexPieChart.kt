package com.jay30.piechartdemo2

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import com.jay30.piechartdemo2.extension.drawConvexArc
import kotlinx.coroutines.launch
import androidx.compose.animation.core.Animatable

@Composable
fun ConvexPieChart(
    modifier: Modifier,
    data: List<PieChartData>,
    startAngle: Float = -90f,
    rotationsCount: Int = 4,
    pieSliceStyle: ConvexStyle = ConvexStyle(),
    animationSpec: AnimationSpec<Float> =
        tween(1_000, easing = LinearOutSlowInEasing)
) {
    // Sum of all data values
    val totalValuesSum = remember(data) { data.sumOf(PieChartData::value) }

    // Animatable values for scaling and rotating the pie chart
    val pieChartScale = remember { Animatable(0f) }
    val pieChartRotation = remember { Animatable(0f) }

    // Launch animations for scaling and rotating the pie chart
    LaunchedEffect(Unit) {
        launch {
            pieChartScale.animateTo(1f, animationSpec)
        }
        launch {
            pieChartRotation.animateTo(360f * rotationsCount, animationSpec)
        }
    }

    // Draw the pie chart using Canvas
    Canvas(
        modifier
            .aspectRatio(1f) // Make sure the canvas is squared (1:1)
            // Apply animation transitions
            .scale(pieChartScale.value)
            .rotate(pieChartRotation.value)
    ) {
        // Initialize last value with the start angle
        var lastValue = startAngle
        // Iterate through each data point and draw corresponding pie slice
        data.forEach { chartData ->
            // Calculate sweep angle for the current data point
            val pieSweepAngle = 360f * (chartData.value.toFloat() / totalValuesSum.toFloat())
            // Draw convex arc representing the pie slice
            drawConvexArc(
                color = chartData.color,
                startAngle = lastValue,
                sweepAngle = pieSweepAngle,
                style = pieSliceStyle,
                useCenter = true
            )
            // Update last value for the next slice
            lastValue += pieSweepAngle
        }
    }
}
