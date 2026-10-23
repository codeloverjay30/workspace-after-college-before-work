package com.jay30.piechartdemo2.examplecode

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jay30.piechartdemo2.ConvexPieChart
import com.jay30.piechartdemo2.PieChartData

@Composable
fun ConvexPieChartComposable(){
    val data = listOf(
        PieChartData(label = "Pie Chart Data 1",value = 20, color = Color.Blue),
        PieChartData(label = "Pie Chart Data 2",value = 50, color = Color.Red),
        PieChartData(label = "Pie Chart Data 3",value = 30, color = Color.Green),
    )

    LazyColumn( modifier = Modifier.fillMaxSize()){
        item{
            ConvexPieChart(
                modifier = Modifier.size(200.dp,200.dp),
                data = data,
                startAngle = 0f,
                rotationsCount = data.size,
            )
        }
    }
}