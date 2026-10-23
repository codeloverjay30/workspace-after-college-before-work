package com.ron.sliderdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ron.sliderdemo.ui.theme.SliderDemoTheme
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SliderDemoTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    val minValue = 0.0f
    val maxValue = 1.0f
    var value by remember { mutableFloatStateOf(0.5f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Opacity: ${String.format(Locale.getDefault(), "%.2f", value)}")
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            // 顯示最小值
            Text(text = "$minValue", modifier = Modifier.padding(8.dp))

            Slider(
                // 目前的值
                value = value,
                // 值的範圍
                valueRange = minValue..maxValue,
                // 值改變時的callback
                onValueChange = { value = it },
                /* weight(1.0f): Row父元件寬度減掉其他子元件必要寬度後，
                    剩下來的所有寬度空間給slider。
                   weight(0.9f): slider佔90%空間，剩下10%分給其他子元件 */
                modifier = Modifier.weight(1.0f),
                colors = SliderDefaults.colors(
                    // 設定thumb顏色
                    thumbColor = Color.DarkGray,
                    // 設定thumb左方值域顏色
                    activeTrackColor = Color.Blue,
                    // 設定thumb右方值域顏色
                    inactiveTrackColor = Color.LightGray
                )
            )

            // 顯示最大值
            Text(text = "$maxValue", modifier = Modifier.padding(8.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = R.drawable.picture),
            contentDescription = "picture",
            alpha = value, // 設定透明度
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.5f) // 設定圖框(非圖片)的寬高比例
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SliderDemoTheme {
        Main()
    }
}