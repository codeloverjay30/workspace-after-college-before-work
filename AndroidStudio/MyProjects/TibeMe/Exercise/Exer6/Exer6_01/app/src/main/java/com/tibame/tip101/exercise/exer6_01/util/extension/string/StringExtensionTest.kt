package com.tibame.tip101.exercise.exer6_01.util.extension.string

object StringExtensionTest {
    fun test(cases:List<String>): List<Boolean> =
        cases.map{
            it.isSafePositiveInteger()
        }
}