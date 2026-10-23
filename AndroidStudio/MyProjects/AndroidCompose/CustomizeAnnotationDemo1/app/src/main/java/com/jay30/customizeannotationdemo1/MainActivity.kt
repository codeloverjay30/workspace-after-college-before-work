package com.jay30.customizeannotationdemo1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.jay30.customizeannotationdemo1.ui.theme.CustomizeAnnotationDemo1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CustomizeAnnotationDemo1Theme {
                MainFrame()
            }
        }
    }
}