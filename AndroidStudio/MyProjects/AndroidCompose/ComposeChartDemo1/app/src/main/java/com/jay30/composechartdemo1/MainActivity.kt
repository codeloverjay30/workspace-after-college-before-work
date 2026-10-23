package com.jay30.composechartdemo1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jay30.composechartdemo1.examplecode.BarChartView
import com.jay30.composechartdemo1.examplecode.LineChartView
import com.jay30.composechartdemo1.examplecode.PieChartView
import com.jay30.composechartdemo1.ui.theme.ComposeChartDemo1Theme
import me.bytebeats.views.charts.bar.BarChart

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeChartDemo1Theme {
                //PieChartView()
                //BarChartView()
                LineChartView()
            }
        }
    }
}
