package com.jay30.focusrequesterdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.jay30.focusrequesterdemo.ui.theme.FocusRequesterDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FocusRequesterDemoTheme {
                FocusRequesterDemoFrame()
            }
        }
    }
}