package com.jay30.piechartdemo1.examplecode

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jay30.piechartdemo1.PieChart

@Composable
fun PieChartComposable(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        // Preview with sample data
        PieChart(
            data = mapOf(
                Pair("Sample-1", 150),
                Pair("Sample-2", 120),
                Pair("Sample-3", 110),
                Pair("Sample-4", 170),
                Pair("Sample-5", 120),
            )
        )
    }
}