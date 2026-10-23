package com.tibame.tip101.exercise.exer3_01

import androidx.compose.foundation.layout.Column

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.tibame.tip101.exercise.exer3_01.util.calculator.SimpleCalculatorTest

object SimpleCalculatorTestUI {
    @Composable
    fun show(){
        val cases = mapOf(
            "12" to "12",
            "13" to "12.3",
            "12.3" to "12.4",
            "" to "1",
            "-1" to "2",
            "-1sfde" to "2wfdcfe",
            "1" to "+2",
            "++1" to "+2",
            "-+1" to "+-2",
            "-+1" to "+(-2)",
        )

        val results = SimpleCalculatorTest.test(cases)

        Column {
            results.forEach{
                Text(it.toString())
                Text("-".repeat(50))
            }
        }
    }
}