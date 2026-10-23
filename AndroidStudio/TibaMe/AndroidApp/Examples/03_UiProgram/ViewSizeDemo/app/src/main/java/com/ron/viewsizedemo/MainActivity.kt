package com.ron.viewsizedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ron.viewsizedemo.ui.theme.ViewSizeDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViewSizeDemoTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    // parent在此是指Column容器，它是A, B, C文字方塊的父元件
    var parentPosition by remember { mutableStateOf(Offset.Zero) }
    var parentWidth by remember { mutableStateOf(0.dp) }
    var parentHeight by remember { mutableStateOf(0.dp) }
    /* 取得目前composable元件的density(密度，是Android UI元件在不同螢幕尺寸的縮放依據)
        取得density方便之後由px(pixels)轉成dp(density-independent pixels) */
    val density = LocalDensity.current
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            // 取得Column的原點座標與尺寸資訊
            .onGloballyPositioned {
                parentPosition = it.positionInRoot()
                // with(density)區塊可以將目前元件尺寸由px轉成dp
                parentWidth = with(density) { it.size.width.toDp() }
                parentHeight = with(density) { it.size.height.toDp() }
            }
    ) {
        Text(
            text = """
        parentPosition: $parentPosition
        parentWidth: $parentWidth
            A(20%): ${parentWidth.times(0.2f)}    
            B(30%): ${parentWidth.times(0.3f)}    
            C(50%): ${parentWidth.times(0.5f)}  
        parentHeight: $parentHeight
            A(20%): ${parentHeight.times(0.2f)}    
            B(30%): ${parentHeight.times(0.3f)}    
            C(50%): ${parentHeight.times(0.5f)}
        """.trimIndent()
        )
        Row {
            Text(
                text = "A",
                modifier = Modifier
                    .width(parentWidth.times(0.2f))
                    .height(parentHeight.times(0.2f))
                    .background(Color.Yellow)
            )

            Text(
                text = "B",
                modifier = Modifier
                    .width(parentWidth.times(0.3f))
                    .height(parentHeight.times(0.3f))
                    .background(Color.Green)
            )

            Text(
                text = "C",
                modifier = Modifier
                    .width(parentWidth.times(0.5f))
                    .height(parentHeight.times(0.5f))
                    .background(Color.Magenta)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ViewSizeDemoTheme {
        Main()
    }
}