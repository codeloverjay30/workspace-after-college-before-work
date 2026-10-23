package com.tibame.tip101.group02.finalproject.healthhelper.util.queryhandling.extension

fun String.isValidString(): Boolean = this.trim().isNotBlank()

fun String.hasLeadingAndTrailing(char: Char = ' '): Boolean =
    this.startsWith(char) && this.endsWith(char)

fun String.insertAtBothSide(println: String): String = "${println}${this}${println}"

fun String.insertAtBothSide(char: Char = ' '): String = this.insertAtBothSide(println = char.toString())

fun String.tryToInsertAtBothSide(char: Char): String =
    if(this.hasLeadingAndTrailing(char))
        this
    else
        this.insertAtBothSide(char)

fun String.patternizeValue():String = this.tryToInsertAtBothSide('\'')

fun String.patternizeTable():String = this.tryToInsertAtBothSide('`')