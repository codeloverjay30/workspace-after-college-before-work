package com.ron.ex05_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ron.ex05_2.ui.theme.Ex05_2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Ex05_2Theme {
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
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                /* graphicsLayer可將內容繪製在新的圖層，這樣可以從父元件分離出來
                    適用於內容要獨立更新時使用 */
                .graphicsLayer {
                    // 使用者操作拖曳手勢時，讓圖片跟隨手指移動
                    translationX = panAmount.x
                    translationY = panAmount.y
                }
                // 讓元件可以感應形變手勢
                .transformable(
                    state = rememberTransformableState { _, panChange, _ ->
                        // 需要累加每次移動量，才能讓元件位移
                        panAmount += panChange
                    }
                )
                .size(120.dp)
                .background(
                    color = Color.Blue,
                    shape = RoundedCornerShape(CornerSize(16f))
                )
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Ex05_2Theme {
        Main()
    }
}