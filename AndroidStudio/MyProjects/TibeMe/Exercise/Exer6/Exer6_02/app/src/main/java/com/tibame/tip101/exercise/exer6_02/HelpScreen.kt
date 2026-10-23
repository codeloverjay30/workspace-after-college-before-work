package com.tibame.tip101.exercise.exer6_02

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HelpScreen(
    message:String,
){
    Column {
        Text(message)
    }
}