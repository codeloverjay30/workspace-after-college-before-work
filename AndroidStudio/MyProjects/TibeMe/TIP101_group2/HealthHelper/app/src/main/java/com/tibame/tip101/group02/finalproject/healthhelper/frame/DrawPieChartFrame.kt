package com.tibame.tip101.group02.finalproject.healthhelper.frame

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.tibame.tip101.group02.finalproject.healthhelper.dataclasses.Item
import com.tibame.tip101.group02.finalproject.healthhelper.util.graph.piechart.PieChartUtil


@Composable
fun DrawPieChartFrame(){
    val currentContext = LocalContext.current
    val items = mutableListOf(
        Item("R", Color.Blue,0.5f),
        Item("Python", Color.Yellow,0.3f),
        Item("Kotlin", Color.Green,0.2f),
    )

    val pieChartUtil = PieChartUtil(currentContext,items)
    pieChartUtil.draw()
}

