package com.tibame.exercise.exercise7.exer7_02.util.bmi

import com.tibame.exercise.exercise7.exer7_02.util.extension.string.isSafePositiveInteger


object BmiUtil {
    fun isValid(
        heightText:String,
        weightText:String,
    ): Boolean =
        heightText.isSafePositiveInteger() && weightText.isSafePositiveInteger()

    fun calculateBMI(
        height: Int,
        weight: Int,
    ): Float = weight.toFloat() / ( ( height.toFloat() / 100f ) * ( height.toFloat() / 100f) )

    fun getHealthStatus(
        bmi : Float
    ): String = when(bmi){
        in 0f ..<18.5f-> "thin"
        in 18.5f ..<24f-> "normal weight"
        in 24f ..< 27f -> "over weight"
        in 27f .. Float.MAX_VALUE -> "fat"
        else -> "error bmi"
    }
}