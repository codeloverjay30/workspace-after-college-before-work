package com.jay30.constraintlayoutdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.jay30.constraintlayoutdemo.frame.ConstraintLayoutDemoFrame
import com.jay30.constraintlayoutdemo.screen.MainScreen
import com.jay30.constraintlayoutdemo.ui.theme.ConstraintLayoutDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ConstraintLayoutDemoTheme {
                MainScreen()
            }
        }
    }
}