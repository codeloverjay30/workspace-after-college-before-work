package com.tibame.tip101.exercise.exer6_03

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tibame.tip101.exercise.exer6_03.ui.theme.Exer6_03Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Exer6_03Theme {
                Main()
            }
        }
    }
}

@Composable
fun Main(){
    MainScreen()
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    Exer6_03Theme {

    }
}