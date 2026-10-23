package com.tibame.tip101.exercise.exer3_01.util.calculator

object SimpleCalculatorTest {
    fun test(
        cases: Map<String,String>
    ): List<Boolean>{
        return cases.map{
            SimpleCalculator.isValidInput(it.key,it.value)
        }
    }
}