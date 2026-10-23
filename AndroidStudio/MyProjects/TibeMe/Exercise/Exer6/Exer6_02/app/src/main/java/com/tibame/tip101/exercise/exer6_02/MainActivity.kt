package com.tibame.tip101.exercise.exer6_02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.tibame.tip101.exercise.exer6_02.ui.theme.Exer6_02Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Exer6_02Theme {
                Main()
            }
        }
    }
}