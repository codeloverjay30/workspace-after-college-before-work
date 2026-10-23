package com.tibame.tip101.group02.finalproject.healthhelper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import com.tibame.tip101.group02.finalproject.healthhelper.consts.stringresourcesingleton.StringResourceSingleton
import com.tibame.tip101.group02.finalproject.healthhelper.frame.DrawPieChartFrame
import com.tibame.tip101.group02.finalproject.healthhelper.ui.theme.HealthHelperTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HealthHelperTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main(){
    Init()
    //MainScreen()
    DrawPieChartFrame()
}

@Composable
fun Init(){
    StringResourceSingleton.initResources()
}