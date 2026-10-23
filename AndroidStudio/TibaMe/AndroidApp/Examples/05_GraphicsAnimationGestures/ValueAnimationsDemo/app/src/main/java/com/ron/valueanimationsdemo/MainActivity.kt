package com.ron.valueanimationsdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateOffset
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.ron.valueanimationsdemo.ui.theme.ValueAnimationsDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ValueAnimationsDemoTheme {
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
    // 動畫如不能預覽，請改用模擬器執行
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TranslateXAnimation()
        }
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TranslateOffsetAnimation()
        }
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            RotateAnimation()
            InfiniteRotateAnimation()
        }
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ScaleAnimation()
            ColorAnimation()
        }
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            MultipleAnimations()
        }
    }
}

// 位移動畫(只改變x的Dp值)
@Composable
fun TranslateXAnimation() {
    // 是否移動
    var moved by remember { mutableStateOf(false) }

    // 建立位移動畫物件(改變位置-Dp值)
    val animatedXOffset by animateDpAsState(
        // 原點向右位移250dp
        targetValue = if (moved) 250.dp else 0.dp,
        // 動畫相關設定：持續時間為500毫秒，採用FastOutSlowInEasing特效(預設)
        animationSpec = tween(500),
        label = "animateDpAsState",
    )

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_soccer),
            contentDescription = "soccer",
            modifier = Modifier
                .size(70.dp)
                // 套用位移動畫物件。因為是水平位移，所以y設為0
                .offset { IntOffset(animatedXOffset.roundToPx(), 0) }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            moved = !moved
        }) {
            Text(text = if (moved) "Back" else "Move")
        }
    }
}

// 位移動畫(改變Offset值)
@Composable
fun TranslateOffsetAnimation() {
    var moved by remember { mutableStateOf(false) }
    // 建立位移動畫物件(改變位置-Offset值)
    val animatedOffset by animateOffsetAsState(
        // x位移250dp, y位移50dp
        targetValue = if (moved) Offset(250f, 50f) else Offset.Zero,
        // 動畫相關設定：持續時間為500毫秒，採用FastOutSlowInEasing特效(預設)
        animationSpec = tween(500),
        label = "animateOffsetAsState"
    )

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_soccer),
            contentDescription = "soccer",
            modifier = Modifier
                .size(70.dp)
                // 套用位移動畫物件。呼叫offset()所提供的lambda需要回傳IntOffset
                .offset {
                    IntOffset(
                        // 套用位移動畫物件的x, y值，但要轉成Int才符合IntOffset()要求
                        animatedOffset.x.dp.roundToPx(),
                        animatedOffset.y.dp.roundToPx()
                    )
                }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            moved = !moved
        }) {
            Text(text = if (moved) "Back\n(Offset)" else "Move\n(Offset)")
        }
    }
}

// 旋轉動畫
@Composable
fun RotateAnimation() {
    var rotateAngle by remember { mutableFloatStateOf(0f) }
    // 建立旋轉動畫物件(改變度數-Float值)；元件的rotate modifier套用後即可產生旋轉動畫
    val animatedRotationAngle by animateFloatAsState(
        // 設定旋轉的目標度數
        targetValue = rotateAngle,
        // 動畫相關設定：持續時間為500毫秒，採用FastOutSlowInEasing特效(預設)
        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing),
        label = "animateFloatAsState"
    )

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_soccer),
            contentDescription = "soccer",
            modifier = Modifier
                .size(70.dp)
                // 套用旋轉動畫物件
                .rotate(animatedRotationAngle)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            // 每點擊一次，順時針旋轉360度
            rotateAngle += 360f
        }) {
            Text(text = "Rotate")
        }
    }
}

// 無限重複旋轉動畫
@Composable
fun InfiniteRotateAnimation() {
    var rotated by remember { mutableStateOf(false) }
    val rotateAngle by remember { mutableFloatStateOf(0f) }
    // 建立無限重複旋轉動畫物件
    val infiniteTransition = rememberInfiniteTransition(label = "InfiniteTransition")
    // 改變度數-Float值
    val animatedRotationAngle by infiniteTransition.animateFloat(
        // 初始角度
        initialValue = 0f,
        // 終止角度
        targetValue = if (rotated) 360f else rotateAngle,
        // 建立無限重複動畫
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 500,
                easing = LinearEasing
            )
        ), label = "infiniteTransition.animateFloat"
    )

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_soccer),
            contentDescription = "soccer",
            modifier = Modifier
                .size(70.dp)
                // 套用無限旋轉動畫物件
                .rotate(animatedRotationAngle)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            rotated = !rotated
        }) {
            Text(text = if (rotated) "Stop" else "Rotate")
        }
    }
}

// 縮放動畫
@Composable
fun ScaleAnimation() {
    var expanded by remember { mutableStateOf(false) }
    // 建立縮放動畫物件(改變尺寸-Dp值)
    val animatedSize by animateDpAsState(
        // 小變大
        targetValue = if (expanded) 120.dp else 70.dp,
        animationSpec = tween(durationMillis = 100),
        label = "animateDpAsState"
    )

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_soccer),
            contentDescription = "soccer",
            modifier = Modifier
                // 套用縮放動畫物件
                .size(animatedSize)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            expanded = !expanded
        }) {
            Text(text = if (expanded) "Shrink" else "Expand")
        }
    }
}

// 色變動畫
@Composable
fun ColorAnimation() {
    var colorChanged by remember { mutableStateOf(false) }
    // 建立色變動畫物件(改變顏色值)
    val animatedColor by animateColorAsState(
        // 紅變藍
        targetValue = if (colorChanged) Color.Blue else Color.Red,
        animationSpec = tween(durationMillis = 1000, easing = LinearEasing),
        label = "animateColorAsState"
    )

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(70.dp)
                // 套用色變動畫物件
                .background(animatedColor, shape = RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            colorChanged = !colorChanged
        }) {
            Text(text = "Switch Color")
        }
    }
}

// 多個動畫
@Composable
fun MultipleAnimations() {
    var animated by remember { mutableStateOf(false) }
    // 建立Transition物件可以加入多個動畫物件
    val transition = updateTransition(
        targetState = animated,
        label = "updateTransition"
    )

    // 位移動畫(如果同時改變x, y值)
    val animatedOffset = transition.animateOffset(
        // 個別動畫相關設定
        transitionSpec = { keyframes { durationMillis = 500 } },
        label = "animateOffset"
    ) {
        // x位移250dp, y位移50dp
        if (it) Offset(250f, 50f) else Offset.Zero
    }

    // 縮放動畫
    val animatedSize = transition.animateDp(
        transitionSpec = { keyframes { durationMillis = 400 } },
        label = "animateDp"
    ) {
        if (it) 90.dp else 50.dp
    }

    // 旋轉動畫
    val animatedRotationAngle =
        transition.animateFloat(
            transitionSpec = { keyframes { durationMillis = 400 } },
            label = "animateFloat"
        ) { if (it) 360f else 0f }

    // 色變動畫
    val animatedColor = transition.animateColor(
        transitionSpec = { keyframes { durationMillis = 300 } },
        label = "animateColor"
    ) {
        if (it) Color.Magenta else Color.Green
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                // 套用位移動畫
                .offset {
                    IntOffset(
                        animatedOffset.value.x.dp.roundToPx(),
                        animatedOffset.value.y.dp.roundToPx()
                    )
                }
                // 套用縮放動畫
                .size(animatedSize.value)
                // 套用旋轉動畫：rotate modifier必須加在此位置，否則不是亂轉就是不轉
                .rotate(animatedRotationAngle.value)
                // 套用色變動畫
                .background(animatedColor.value, shape = RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            animated = !animated
        }) {
            Text(text = if (animated) "Back" else "Mixed")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ValueAnimationsDemoTheme {
        Main()
    }
}