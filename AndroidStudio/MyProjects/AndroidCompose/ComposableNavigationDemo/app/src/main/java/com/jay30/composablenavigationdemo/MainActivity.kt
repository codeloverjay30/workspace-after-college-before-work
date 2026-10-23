package com.jay30.composablenavigationdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.jay30.composablenavigationdemo.ui.theme.ComposableNavigationDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposableNavigationDemoTheme {
                ComposableNavigationDemoScreen()
            }
        }
    }
}