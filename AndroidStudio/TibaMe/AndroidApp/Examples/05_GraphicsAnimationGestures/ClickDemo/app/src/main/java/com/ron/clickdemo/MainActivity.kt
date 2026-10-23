package com.ron.clickdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ron.clickdemo.ui.theme.ClickDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ClickDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Main(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Main(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ClickableDemo()
        Spacer(modifier = Modifier.height(16.dp))
        CombinedClickableDemo()
    }
}

@Composable
fun ClickableDemo() {
    var clicked by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .background(
                color = if (clicked) Color.Yellow else Color.Green,
                shape = RoundedCornerShape(8.dp)
            )
            // modifier加上clickable能讓元件感應點擊事件
            .clickable {
                clicked = !clicked
            },
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = if (clicked) "Clicked!" else "Clickable",
            modifier = Modifier.padding(16.dp)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CombinedClickableDemo() {
    var text by remember { mutableStateOf("Combined") }
    var color by remember { mutableStateOf(Color.LightGray) }
    Box(
        modifier = Modifier
            .background(
                color = color,
                shape = RoundedCornerShape(8.dp)
            )
            // modifier加上combinedClickable能讓元件感應點擊、雙擊與長按事件
            .combinedClickable(
                onClick = {
                    text = "Clicked!"
                    color = Color.Yellow
                },
                onDoubleClick = {
                    text = "Double Clicked!"
                    color = Color.Cyan
                },
                onLongClick = {
                    text = "Long Clicked!"
                    color = Color.Magenta
                },
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ClickDemoTheme {
        Main()
    }
}