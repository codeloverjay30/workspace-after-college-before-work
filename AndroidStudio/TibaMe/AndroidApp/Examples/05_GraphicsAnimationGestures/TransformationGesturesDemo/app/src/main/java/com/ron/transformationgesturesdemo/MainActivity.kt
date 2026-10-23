package com.ron.transformationgesturesdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ron.transformationgesturesdemo.ui.theme.TransformationGesturesDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TransformationGesturesDemoTheme {
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
    var panAmount by remember { mutableStateOf(Offset.Zero) }
    var rotationAmount by remember { mutableFloatStateOf(0f) }
    var zoomAmount by remember { mutableFloatStateOf(1f) }
    var text by remember { mutableStateOf("messages") }
    Text(text = text, modifier = Modifier.padding(64.dp))
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.image),
            contentDescription = "image",
            modifier = Modifier
                /* graphicsLayer可將內容繪製在新的圖層，這樣可以從父元件分離出來
                    適用於內容要獨立更新時使用 */
                .graphicsLayer {
                    // 使用者操作拖曳手勢時，讓圖片跟隨手指移動
                    translationX = panAmount.x
                    translationY = panAmount.y

                    // 使用者操作旋轉手勢時，讓圖片跟著旋轉
                    rotationZ = rotationAmount

                    // 使用者操作縮放手勢時，讓圖片跟著等比例縮放
                    scaleX = zoomAmount
                    scaleY = zoomAmount
                }
                // 讓元件可以感應形變手勢
                .transformable(
                    state = rememberTransformableState { zoomChange, panChange, rotationChange ->
                        // 需要累加每次移動量，才能讓元件位移
                        panAmount += panChange
                        // 需要累加每次旋轉角度，才能讓元件旋轉
                        rotationAmount += rotationChange
                        // 需要累乘每次縮放量，才能讓元件縮放
                        zoomAmount *= zoomChange
                        text = "pan: $panAmount\nrotation: $rotationAmount\nzoom: $zoomAmount"
                    }
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TransformationGesturesDemoTheme {
        Main()
    }
}