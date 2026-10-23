package com.tibame.exercise.exer7.exer7_01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.tibame.exercise.exer7.exer7_01.ui.theme.Ex7_01Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Ex7_01Theme {
                Main()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Ex7_01Theme {
        Main()
    }
}