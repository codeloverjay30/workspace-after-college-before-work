package com.tibame.tip101.exercise.exer6_01.util.extension.string

fun String.isSafePositiveInteger(): Boolean {
    val intValue : Int = this.toIntOrNull() ?: -1
    return intValue > 0
}