package com.jay30.composechartdemo1.examplecode

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jay30.composechartdemo1.util.randomYValue
import me.bytebeats.views.charts.line.LineChart
import me.bytebeats.views.charts.line.LineChartData
import me.bytebeats.views.charts.line.render.line.SolidLineDrawer
import me.bytebeats.views.charts.line.render.point.FilledCircularPointDrawer
import me.bytebeats.views.charts.line.render.xaxis.SimpleXAxisDrawer
import me.bytebeats.views.charts.line.render.yaxis.SimpleYAxisDrawer
import me.bytebeats.views.charts.simpleChartAnimation

@Composable
fun LineChartView() {
    LineChart(
        lineChartData = LineChartData(
            points = listOf(
                LineChartData.Point(randomYValue(), "Line 1"),
                LineChartData.Point(randomYValue(), "Line 2"),
                LineChartData.Point(randomYValue(), "Line 3"),
                LineChartData.Point(randomYValue(), "Line 4"),
                LineChartData.Point(randomYValue(), "Line 5"),
            )
        ),
        // Optional properties.
        modifier = Modifier.size(400.dp,800.dp),
        animation = simpleChartAnimation(),
        pointDrawer = FilledCircularPointDrawer(),
        lineDrawer = SolidLineDrawer(),
        xAxisDrawer = SimpleXAxisDrawer(),
        yAxisDrawer = SimpleYAxisDrawer(),
        horizontalOffset = 5f
    )
}