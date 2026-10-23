package com.jay30.composechartdemo1.util

import 	android.graphics.Color
import kotlin.random.Random

fun randomColor(): Color {
    val randomNumber1 = Random(1).nextFloat() * 256
    val randomNumber2 = Random(1).nextFloat() * 256
    val randomNumber3 = Random(1).nextFloat() * 256
    val randomNumber4 = Random(1).nextFloat() * 256
    return Color.valueOf(randomNumber1,randomNumber2,randomNumber3,randomNumber4)
}