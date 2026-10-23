package com.tibame.tip101.exercise.exer3_01.util.calculator

import kotlin.math.abs

object SimpleCalculator {
    fun isValidInput(
        number1Text : String,
        number2Text : String,
    ):Boolean{
        try{
           val number1 = number1Text.toInt()
           val number2 = number1Text.toInt()
        }catch (ex:Exception){
            return false
        }
        return true
    }

    fun canNotDivide(
       number1: Float,
       number2: Float,
    ):Boolean = abs(number2) <= 0.0001
}