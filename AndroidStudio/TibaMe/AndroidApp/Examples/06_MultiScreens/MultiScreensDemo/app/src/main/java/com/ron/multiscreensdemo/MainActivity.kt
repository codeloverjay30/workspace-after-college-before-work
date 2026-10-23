package com.ron.multiscreensdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ron.multiscreensdemo.ui.theme.MultiScreensDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MultiScreensDemoTheme {
                Main()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MultiScreensDemoTheme {
        Main()
    }
}