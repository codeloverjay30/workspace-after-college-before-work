package com.ron.ex06_3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ron.ex06_3.ui.theme.Ex06_3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ex06_3Theme {
                Main()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Ex06_3Theme {
        Main()
    }
}