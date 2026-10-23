package com.tibame.exercise.exercise7.exer7_02.util.extension.string

fun String.isSafePositiveInteger(): Boolean {
    val intValue : Int = this.toIntOrNull() ?: -1
    return intValue > 0
}