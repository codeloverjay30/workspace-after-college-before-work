package com.jay30.composechartdemo1.examplecode

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jay30.composechartdemo1.util.randomColor
import com.jay30.composechartdemo1.util.randomValue
import me.bytebeats.views.charts.bar.BarChart
import me.bytebeats.views.charts.bar.BarChartData
import me.bytebeats.views.charts.bar.render.bar.SimpleBarDrawer
import me.bytebeats.views.charts.bar.render.label.SimpleLabelDrawer
import me.bytebeats.views.charts.bar.render.xaxis.SimpleXAxisDrawer
import me.bytebeats.views.charts.bar.render.yaxis.SimpleYAxisDrawer
import me.bytebeats.views.charts.simpleChartAnimation

@Composable
fun BarChartView() {
    BarChart(
        barChartData = BarChartData(
            bars = listOf(
                BarChartData.Bar(
                    label = "Bar 1",
                    value = randomValue(),
                    color = Color.Blue
                ),
                BarChartData.Bar(
                    label = "Bar 2",
                    value = randomValue(),
                    color = Color.Blue
                ),
                BarChartData.Bar(
                    label = "Bar 3",
                    value = randomValue(),
                    color = Color.Blue
                ),
                BarChartData.Bar(
                    label = "Bar 4",
                    value = randomValue(),
                    color = Color.Blue
                ),
            )
        ),
        // Optional properties.
        modifier = Modifier.size(400.dp,800.dp).offset(0.dp,100.dp),
        animation = simpleChartAnimation(),
        barDrawer = SimpleBarDrawer(),
        xAxisDrawer = SimpleXAxisDrawer(),
        yAxisDrawer = SimpleYAxisDrawer(),
        labelDrawer = SimpleLabelDrawer()
    )
}