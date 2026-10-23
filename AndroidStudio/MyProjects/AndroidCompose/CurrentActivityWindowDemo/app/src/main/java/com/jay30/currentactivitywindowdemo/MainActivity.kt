package com.jay30.currentactivitywindowdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.jay30.currentactivitywindowdemo.ui.theme.CurrentActivityWindowDemoTheme
import androidx.compose.ui.platform.LocalContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CurrentActivityWindowDemoTheme {
                val currentActivity = LocalContext.current as Activity
                // currentActivity is the current activity (an Activity instance)
                CurrentActivityWindowDemoFrame(currentActivity)
            }
        }
    }
}