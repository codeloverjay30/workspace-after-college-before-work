package com.jay30.jetchartdemo1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jay30.jetchartdemo1.component.divider.JetDivider
import com.jay30.jetchartdemo1.examplecode.BarChartComposable
import com.jay30.jetchartdemo1.examplecode.DonutChartComposable
import com.jay30.jetchartdemo1.examplecode.GaugeChartComposable
import com.jay30.jetchartdemo1.examplecode.LineChartComposable
import com.jay30.jetchartdemo1.examplecode.PieChartComposable
import com.jay30.jetchartdemo1.ui.theme.JetChartDemo1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetChartDemo1Theme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val text = remember { mutableStateOf("Click on a bar!") }
                    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                        Text(text.value, modifier = Modifier.padding(16.dp))
                        BarChartComposable(text)
                        JetDivider()
                        LineChartComposable()
                        JetDivider()
                        PieChartComposable()
                        JetDivider()
                        DonutChartComposable()
                        JetDivider()
                        GaugeChartComposable()
                        JetDivider()
                    }
                }
            }
        }
    }
}