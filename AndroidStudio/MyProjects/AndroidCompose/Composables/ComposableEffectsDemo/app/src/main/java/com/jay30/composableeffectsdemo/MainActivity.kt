package com.jay30.composableeffectsdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.jay30.composableeffectsdemo.screen.MainScreen
import com.jay30.composableeffectsdemo.ui.theme.ComposableEffectsDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposableEffectsDemoTheme {
                MainScreen()
            }
        }
    }
}
