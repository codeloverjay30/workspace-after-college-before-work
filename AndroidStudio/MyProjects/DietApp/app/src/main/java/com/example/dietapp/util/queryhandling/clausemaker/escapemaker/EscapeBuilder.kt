package com.example.dietapp.util.queryhandling.clausemaker.escapemaker

import com.example.dietapp.extension.string.patternizeValue

/**
 * EscapeBuilder
 *
 * @constructor A builder that create a escape clause about MySQL.
 *              It can be concatenated after `LIKE` clause
 */

class EscapeBuilder() {
    private var textToEscape: String = ""

    fun escape(textToEscape: String):EscapeBuilder{
        assert(textToEscape.isNotBlank())
        this.textToEscape = textToEscape.patternizeValue()
        return this
    }

    fun build(): String{
        val stringBuilder = StringBuilder()
        stringBuilder.append(" ESCAPE ")
        stringBuilder.append(this.textToEscape)
        return stringBuilder.toString()
    }
}