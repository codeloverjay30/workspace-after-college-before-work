package com.tibame.tip101.exercise.exer6_01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.tibame.tip101.exercise.exer6_01.ui.MainScreen
import com.tibame.tip101.exercise.exer6_01.ui.theme.Exer6_01_v2Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Exer6_01_v2Theme {
                MainScreen.show()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Exer6_01_v2Theme {
        MainScreen.show()
    }
}