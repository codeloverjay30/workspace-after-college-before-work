package com.example.dietapp.extension.string

fun String.isValidString(): Boolean = this.trim().isNotBlank()

fun String.hasLeadingAndTrailing(char: Char = ' '): Boolean =
    this.startsWith(char) && this.endsWith(char)

fun String.insertAtBothSide(text: String): String = "${text}${this}${text}"

fun String.insertAtBothSide(char: Char = ' '): String = this.insertAtBothSide(text = char.toString())

fun String.tryToInsertAtBothSide(char: Char): String =
    if(this.hasLeadingAndTrailing(char))
        this
    else
        this.insertAtBothSide(char)

fun String.patternizeValue():String = this.tryToInsertAtBothSide('\'')

fun String.patternizeTable():String = this.tryToInsertAtBothSide('`')