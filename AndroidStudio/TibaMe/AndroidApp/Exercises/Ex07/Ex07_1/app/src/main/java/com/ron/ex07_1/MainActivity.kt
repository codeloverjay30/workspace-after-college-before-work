package com.ron.ex07_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ron.ex07_1.ui.theme.Ex07_1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Ex07_1Theme {
                Main()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Ex07_1Theme {
        Main()
    }
}