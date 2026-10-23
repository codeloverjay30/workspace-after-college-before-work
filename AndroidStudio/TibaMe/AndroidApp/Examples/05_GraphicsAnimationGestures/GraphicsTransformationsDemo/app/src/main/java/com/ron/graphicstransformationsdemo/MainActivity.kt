package com.ron.graphicstransformationsdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ron.graphicstransformationsdemo.ui.theme.GraphicsTransformationsDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GraphicsTransformationsDemoTheme {
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
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TranslateDemo()
        RotateDemo()
        ScaleDemo()
    }
}

@Composable
fun TranslateDemo() {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color.LightGray)
    ) {
        // 先在原始位置繪圖，預設圓心為this.center，會將圓形置中
        drawCircle(Color.Gray, radius = 200f)

        // 新的繪圖位置為原始位置左移100像素，下移50像素，位移完畢再繪製新的圓形
        translate(left = -100f, top = 50f) {
            drawCircle(Color.Red, radius = 200f)
        }
    }
}

@Composable
fun RotateDemo() {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color.LightGray)
    )
    // onDraw參數：傳入lambda以提供欲繪製的圖形
    {
        // 原始位置
        drawRect(
            Color.Gray,
            // 預設矩形原點為Offset.Zero，即為左上角；為了讓矩形置中對齊而做修改
            topLeft = Offset(x = size.width / 3f, y = size.height / 3f),
            size = size / 3f
        )

        // 新的繪圖位置為原始位置旋轉45度
        rotate(degrees = 45f) {
            drawRect(
                Color.Green,
                topLeft = Offset(x = size.width / 3f, y = size.height / 3f),
                size = size / 3f
            )
        }
    }
}

@Composable
fun ScaleDemo() {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color.LightGray)
    ) {
        // 等比例放大10倍
        scale(10f) {
            drawCircle(Color.Blue, radius = 20f)
        }
        // 寬放大5倍；高放大10倍
//        scale(scaleX = 5f, scaleY = 10f) {
//            drawCircle(Color.Blue, radius = 20f)
//        }

        // 沒有呼叫scale()會以原始大小繪製
        drawCircle(Color.Gray, radius = 20f)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GraphicsTransformationsDemoTheme {
        Main()
    }
}