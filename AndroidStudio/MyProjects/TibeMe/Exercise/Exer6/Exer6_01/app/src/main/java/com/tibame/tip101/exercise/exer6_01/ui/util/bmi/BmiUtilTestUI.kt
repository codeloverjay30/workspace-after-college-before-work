package com.tibame.tip101.exercise.exer6_01.ui.util.bmi

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.tibame.tip101.exercise.exer6_01.util.bmi.BmiUtilTest

object BmiUtilTestUI {
    @Composable
    fun show(){
        val cases = mapOf(
            "18.5" to "29",
            "18.5" to "29.5",
            "18" to "29",
            "1" to "29",
            "-1" to "29",
            "1" to "-29",
            "-1" to "-29",
            "0" to "0",
            "2" to "2",
        )

        val results = BmiUtilTest.test(cases)
        Column {
            results.forEach {
                Text(it.toString())
            }
        }
    }
}