package com.tibame.tip101.group02.finalproject.healthhelper.util.graph.piechart

import android.content.Context
import com.tibame.tip101.group02.finalproject.healthhelper.dataclasses.Item
import org.eazegraph.lib.charts.PieChart
import org.eazegraph.lib.models.PieModel

class PieChartUtil(
    val context: Context,
    val items: List<Item>,
){
    val pieChart = PieChart(context)
    val pieModels: MutableList<PieModel> = mutableListOf()
    init{
         items.map{ item ->
            pieModels.add(
                PieModel(item.name,item.weight,item.color.value.toInt())
            )
        }

        pieModels.forEach { pieModel ->
            pieChart.addPieSlice(pieModel)
        }
    }

    fun draw(){
        pieChart.startAnimation()
    }
}