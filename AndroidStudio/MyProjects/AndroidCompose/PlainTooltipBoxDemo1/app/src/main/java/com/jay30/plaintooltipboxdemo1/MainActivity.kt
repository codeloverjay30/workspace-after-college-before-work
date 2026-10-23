package com.jay30.plaintooltipboxdemo1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.jay30.plaintooltipboxdemo1.ui.theme.PlainTooltipBoxDemo1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlainTooltipBoxDemo1Theme {
                MainFrame()
            }
        }
    }
}