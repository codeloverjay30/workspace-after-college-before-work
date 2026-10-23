package com.jay30.composepreferencedemo2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.jay30.composepreferencedemo2.frame.MainFrame
import com.jay30.composepreferencedemo2.screen.MainScreen
import com.jay30.composepreferencedemo2.ui.theme.ComposePreferenceDemo2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposePreferenceDemo2Theme {
                MainScreen()
            }
        }
    }
}