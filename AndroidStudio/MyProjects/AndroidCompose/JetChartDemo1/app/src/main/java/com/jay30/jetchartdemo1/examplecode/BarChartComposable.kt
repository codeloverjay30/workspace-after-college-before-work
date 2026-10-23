package com.jay30.jetchartdemo1.examplecode

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import io.jetchart.bar.renderer.label.SimpleBarValueDrawer.ValueDrawLocation.Inside
import androidx.compose.ui.unit.dp
import io.jetchart.bar.Bar
import io.jetchart.bar.BarChart
import io.jetchart.bar.Bars
import io.jetchart.bar.renderer.label.SimpleBarLabelDrawer
import io.jetchart.bar.renderer.label.SimpleBarValueDrawer
import io.jetchart.bar.renderer.xaxis.BarXAxisDrawer
import io.jetchart.bar.renderer.yaxis.BarYAxisWithValueDrawer
import io.jetchart.common.animation.fadeInAnimation
import kotlin.random.Random

@Composable
fun BarChartComposable(text: MutableState<String>) {
    val numberOfBars = 8
    val width = numberOfBars * 80
    BarChart(bars = Bars(
        bars = (1..numberOfBars).map {
            Bar(label = "BAR$it", value = Random.nextFloat(), color = Color.Blue) {
                    bar -> text.value = "You clicked on the bar ${bar.label}!"
            }
        }),
        modifier = Modifier.horizontalScroll(rememberScrollState()).width(width.dp).height(500.dp),
        animation = fadeInAnimation(3000),
        xAxisDrawer = BarXAxisDrawer(),
        yAxisDrawer = BarYAxisWithValueDrawer(),
        labelDrawer = SimpleBarLabelDrawer(),
        valueDrawer = SimpleBarValueDrawer(drawLocation = Inside),
        barHorizontalMargin = 5.dp
    )
}