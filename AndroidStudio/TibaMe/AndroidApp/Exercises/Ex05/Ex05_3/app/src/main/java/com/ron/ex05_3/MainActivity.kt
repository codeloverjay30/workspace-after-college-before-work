package com.ron.ex05_3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ron.ex05_3.ui.theme.Ex05_3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Ex05_3Theme {
                Main(
                )
            }
        }
    }
}

@Composable
fun Main() {
    var zoomAmount by remember { mutableFloatStateOf(1f) }
    // 初始字型尺寸為50
    val initialFontSize = 50
    var text by remember { mutableStateOf("$initialFontSize") }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Hello",
            // 新的字型尺寸 = 初始尺寸 x 放大比例
            fontSize = (initialFontSize * zoomAmount).sp,
            modifier = Modifier
                // 讓元件可以感應形變手勢
                .transformable(
                    state = rememberTransformableState { zoomChange, _, _ ->
                        zoomAmount *= zoomChange
                        // 顯示字型尺寸
                        text = "${(initialFontSize * zoomAmount).toInt()}"
                    }
                )
        )
        Text(text = text, modifier = Modifier.padding(8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Ex05_3Theme {
        Main()
    }
}