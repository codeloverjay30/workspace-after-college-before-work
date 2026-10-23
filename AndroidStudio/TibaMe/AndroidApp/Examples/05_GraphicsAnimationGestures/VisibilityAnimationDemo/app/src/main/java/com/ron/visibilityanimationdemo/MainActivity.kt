package com.ron.visibilityanimationdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ron.visibilityanimationdemo.ui.theme.VisibilityAnimationDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VisibilityAnimationDemoTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            AnimateAlpha()
        }
        Column(modifier = Modifier.fillMaxWidth()) {
            AnimatedVisibilityFun()
        }
        Column(modifier = Modifier.fillMaxWidth()) {
            AnimateContentSize()
        }
    }
}

@Composable
fun AnimateAlpha() {
    var visible by remember { mutableStateOf(true) }

    Column {
        Box(
            // 只將元件設為透明，所以下方元件不會自動補上
            modifier = Modifier
                /* graphicsLayer可將內容繪製在新的圖層，這樣可以從父元件分離出來
                    適用於內容要獨立更新時使用 */
                .graphicsLayer {
                    alpha = if (visible) 1.0f else 0f
                }
                .fillMaxWidth()
                .height(100.dp)
                .background(colorResource(id = R.color.teal_200))
        )
        Spacer(modifier = Modifier.height(5.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(colorResource(id = R.color.purple_200))
                .clickable { visible = !visible }
        )
    }
}

@Composable
fun AnimatedVisibilityFun() {
    var visible by remember { mutableStateOf(true) }
    Column {
        /* AnimatedVisibility有enter與exit參數，設定進入與離開動畫。
        enter可採用: FadeIn(), slideIn(), SlideInHorizontally(), slideInVertically(), scaleIn()
        exit可採用: fadeOut(), slideOut(), slideOutHorizontally(), slideOutVertically()
        enter預設為: fadeIn() + expandVertically()
        exit預設為：fadeOut() + shrinkVertically()
        該元件隱藏時也會被移除，所以下方元件會自動補上 */
        AnimatedVisibility(visible) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(colorResource(id = R.color.teal_200))
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(colorResource(id = R.color.purple_200))
                .clickable { visible = !visible }
        )
    }
}

@Composable
fun AnimateContentSize() {
    var expanded by remember { mutableStateOf(true) }

    Column {
        Box(
            modifier = Modifier
                .background(colorResource(id = R.color.teal_200))
                /* 改變內容尺寸，需放在尺寸的modifier(例如height)之前。
                    元件縮短時，下方元件會自動補上 */
                .animateContentSize()
                .height(if (expanded) 100.dp else 0.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(5.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(colorResource(id = R.color.purple_200))
                .clickable { expanded = !expanded }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    VisibilityAnimationDemoTheme {
        Main()
    }
}