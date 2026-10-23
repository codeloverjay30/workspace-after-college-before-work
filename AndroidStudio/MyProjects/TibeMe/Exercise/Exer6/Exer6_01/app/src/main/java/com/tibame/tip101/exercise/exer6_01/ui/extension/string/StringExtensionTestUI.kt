package com.tibame.tip101.exercise.exer6_01.ui.extension.string

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.tibame.tip101.exercise.exer6_01.util.extension.string.StringExtensionTest

object StringExtensionTestUI {
    @Composable
    fun show(){
        val cases = listOf(
            "",
            "123",
            " 123 ",
            "\t456\t",
            "\n789\n",
            "z12b",
            "z12_12",
            "12.1",
            "12.1.2",
        )

        val results = StringExtensionTest.test(cases)
        Column {
            results.forEach{
                Text(it.toString())
                Text("-".repeat(50))
            }
        }
    }
}