package com.jay30.insetmodifierdemo1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.jay30.insetmodifierdemo1.ui.theme.InsetModifierDemo1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InsetModifierDemo1Theme {
                MainFrame()
            }
        }
    }
}