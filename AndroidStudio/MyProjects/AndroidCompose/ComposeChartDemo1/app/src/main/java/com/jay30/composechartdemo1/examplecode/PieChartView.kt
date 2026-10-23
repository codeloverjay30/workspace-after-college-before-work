package com.jay30.composechartdemo1.examplecode

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jay30.composechartdemo1.util.randomColor
import com.jay30.composechartdemo1.util.randomLength
import me.bytebeats.views.charts.pie.PieChart
import me.bytebeats.views.charts.pie.PieChartData
import me.bytebeats.views.charts.pie.render.SimpleSliceDrawer
import me.bytebeats.views.charts.simpleChartAnimation

@Composable
fun PieChartView() {
    PieChart(
        pieChartData = PieChartData(
            slices = listOf(
                PieChartData.Slice(
                    randomLength(),
                    Color.Green
                ),
                PieChartData.Slice(randomLength(), Color.Blue),
                PieChartData.Slice(randomLength(), Color.Red)
            )
        ),
        // Optional properties.
        modifier = Modifier.size(200.dp,200.dp),
        animation = simpleChartAnimation(),
        sliceDrawer = SimpleSliceDrawer()
    )
}


