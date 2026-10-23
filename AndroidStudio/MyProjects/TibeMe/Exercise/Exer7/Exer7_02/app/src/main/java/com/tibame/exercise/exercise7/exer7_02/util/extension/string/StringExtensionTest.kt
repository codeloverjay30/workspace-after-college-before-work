package com.tibame.exercise.exercise7.exer7_02.util.extension.string

object StringExtensionTest {
    fun test(cases:List<String>): List<Boolean> =
        cases.map{
            it.isSafePositiveInteger()
        }
}