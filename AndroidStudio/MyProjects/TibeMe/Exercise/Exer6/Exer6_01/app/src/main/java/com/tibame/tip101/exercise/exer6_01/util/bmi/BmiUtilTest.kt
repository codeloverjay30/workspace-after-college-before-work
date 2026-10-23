package com.tibame.tip101.exercise.exer6_01.util.bmi

object BmiUtilTest {
    fun test(cases: Map<String, String>): List<Boolean> =
        cases.map {
            BmiUtil.isValid(it.key, it.value)
        }
}