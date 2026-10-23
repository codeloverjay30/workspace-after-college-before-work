package com.ron.ex04_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ron.ex04_1.ui.theme.Ex04_1Theme
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Ex04_1Theme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    var fontSize by remember { mutableFloatStateOf(30.0f) }
    var red by remember { mutableFloatStateOf(0.5f) }
    var green by remember { mutableFloatStateOf(0.5f) }
    var blue by remember { mutableFloatStateOf(0.5f) }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp, vertical = 32.dp)

    ) {
        Row {
            Slider(
                // 目前的值
                value = fontSize,
                // 值的範圍
                valueRange = 0.0f .. 100.0f,
                // 值改變時的callback
                onValueChange = { fontSize = it },
                /* weight(1.0f): Row父元件寬度減掉其他子元件必要寬度後，
                    剩下來的所有寬度空間給slider。
                   weight(0.9f): slider佔90%空間，剩下10%分給其他子元件 */
                modifier = Modifier.weight(1.0f),
                colors = SliderDefaults.colors(
                    thumbColor = Color.White,
                    activeTrackColor = Color.Black
                )
            )
            Text(
                text = String.format(Locale.getDefault(), "%.2f", fontSize),
                modifier = Modifier.padding(8.dp)
            )
        }
        Row {
            Slider(
                value = red,
                valueRange = 0.0f .. 1.0f,
                onValueChange = { red = it },
                modifier = Modifier.weight(1.0f),
                colors = SliderDefaults.colors(
                    thumbColor = Color.White,
                    activeTrackColor = Color.Red
                )
            )
            Text(
                text = String.format(Locale.getDefault(), "%.2f", red),
                modifier = Modifier.padding(8.dp)
            )
        }
        Row {
            Slider(
                value = green,
                valueRange = 0.0f .. 1.0f,
                onValueChange = { green = it },
                modifier = Modifier.weight(1.0f),
                colors = SliderDefaults.colors(
                    thumbColor = Color.White,
                    activeTrackColor = Color.Green
                )
            )
            Text(
                text = String.format(Locale.getDefault(), "%.2f", green),
                modifier = Modifier.padding(8.dp)
            )
        }
        Row {
            Slider(
                value = blue,
                valueRange = 0.0f .. 1.0f,
                onValueChange = { blue = it },
                modifier = Modifier.weight(1.0f),
                colors = SliderDefaults.colors(
                    thumbColor = Color.White,
                    activeTrackColor = Color.Blue
                )
            )
            Text(
                text = String.format(Locale.getDefault(), "%.2f", blue),
                modifier = Modifier.padding(8.dp)
            )
        }
        Text(
            text = "Text",
            fontSize = fontSize.sp,
            color = Color(red, green, blue)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Ex04_1Theme {
        Main()
    }
}