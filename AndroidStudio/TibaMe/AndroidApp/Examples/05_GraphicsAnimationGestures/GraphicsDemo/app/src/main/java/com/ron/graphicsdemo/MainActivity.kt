package com.ron.graphicsdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ron.graphicsdemo.ui.theme.GraphicsDemoTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GraphicsDemoTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    // 設定點擊按鈕後圖形移動距離
    var distance by remember { mutableFloatStateOf(0f) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            // 點擊左移按鈕會左移30單位
            Button(
                modifier = Modifier.padding(end = 8.dp),
                onClick = { distance -= 30f }
            ) {
                Text(text = stringResource(id = R.string.strLeft))
            }
            // 點擊右移按鈕會右移30單位
            Button(onClick = { distance += 30f }) {
                Text(text = stringResource(id = R.string.strRight))
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            DrawRectLineCircleDemo(distance)
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            DrawOvalDemo()
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            DrawArcDemo()
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            DrawPointsDemo()
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.LightGray)
        ) {
            DrawPathDemo()
        }
    }
}

/**
 * 繪製矩形、直線與圓形
 * distance: 移動距離
 */
@Composable
fun DrawRectLineCircleDemo(distance: Float) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        // 繪製矩形
        drawRect(
            // 設定垂直漸層
            brush = Brush.linearGradient(
                listOf(Color.Blue, Color.Red, Color.Yellow),
                // 設定漸層開始與結束位置
                start = Offset(0.0f, 0.0f),
                end = Offset(0.0f, size.height)
            ),
            // 預設矩形原點為Offset.Zero，也就是Offset(0f, 0f)，即為左上角
            topLeft = Offset.Zero,
            // size為目前所在容器的尺寸(就是上方Main()內容的Column容器)
            size = size
        )
        // 繪製直線
        drawLine(
            color = Color.Magenta,
            start = Offset(x = 10f + distance, y = 10f),
            end = Offset(x = 210f + distance, y = 10f),
            // 線的粗細
            strokeWidth = 10f
        )
        // 繪製圓形
        drawCircle(
            color = Color.Green,
            // 預設圓心為this.center，會將圓形置中
            center = Offset(x = 110f + distance, y = 140f),
            // 圓的半徑
            radius = 100f
        )
    }
}

@Composable
fun DrawOvalDemo() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        // 繪製橢圓形
        drawOval(
            // 水平漸層
            brush = Brush.horizontalGradient(listOf(Color.Black, Color.LightGray)),
            // 使用指定寬高建立Size物件
            size = Size(size.width, size.height)
        )
    }
}

@Composable
fun DrawArcDemo() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        // 繪製弧形
        drawArc(
            color = Color.Blue,
            // 開始度數，0代表3點鐘方向
            startAngle = 0f,
            // 順時針畫出280度；也就是開始度數 + 280度
            sweepAngle = 280f,
            // 弧形的閉合是否以中心點為主
            useCenter = true,
            size = Size(size.height, size.height)
        )
    }
}

@Composable
fun DrawPointsDemo() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val width = size.width
        val height = size.height
        // 繪製多點
        drawPoints(
            // 提供要繪製的點座標
            points = listOf(
                Offset(width / 2, 0f),
                Offset(width / 2, height / 2),
                Offset(width / 2, height)
            ),
            pointMode = PointMode.Points,
            // pointMode = PointMode.Lines, // Lines: 單數點，最後一點不繪製
            // pointMode = PointMode.Polygon, // Polygon: 單數點，最後一點仍繪製
            color = Color.Red,
            strokeWidth = 50f,
            // 點的形狀
            cap = StrokeCap.Round
        )
    }
}

@Composable
fun DrawPathDemo() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val path = Path()
        // 路徑起始點座標
        path.moveTo(0f, 30f)
        // 提供下個點座標，即可繪製線段
        path.lineTo(size.width / 2f, size.height / 2f)
        path.lineTo(size.width, 30f)
        // 路徑結束
        path.close()
        // 繪製路徑
        drawPath(
            path = path,
            color = Color.Green,
            style = Stroke(width = 10f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GraphicsDemoTheme {
        Main()
    }
}