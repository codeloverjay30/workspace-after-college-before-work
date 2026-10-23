package com.jay30.barchartdemo

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BarChartDemoFrame(){
    MaterialTheme {
        BarChart.drawWithMinValueAndMaxValue(
            dataPoints = listOf(4, 24, 15),
            maxText = { Text("Max") },
            minText = { Text("Min") },
            modifier = Modifier.padding(24.dp)
        )
    }
}

@Preview
@Composable
private fun ChartDataPreview() {
    MaterialTheme {
        BarChart.drawWithMinValueAndMaxValue(
            dataPoints = listOf(4, 24, 15),
            maxText = { Text("Max") },
            minText = { Text("Min") },
            modifier = Modifier.padding(24.dp)
        )
    }
}

