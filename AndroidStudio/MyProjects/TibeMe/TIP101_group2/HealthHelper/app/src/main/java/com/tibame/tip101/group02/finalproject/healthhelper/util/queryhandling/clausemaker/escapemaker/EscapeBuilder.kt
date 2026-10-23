package com.tibame.tip101.group02.finalproject.healthhelper.util.queryhandling.clausemaker.escapemaker

import com.tibame.tip101.group02.finalproject.healthhelper.util.queryhandling.extension.patternizeValue

/**
 * EscapeBuilder
 *
 * @constructor A builder that create a escape clause about MySQL.
 *              It can be concatenated after `LIKE` clause
 */

class EscapeBuilder() {
    private var printlnToEscape: String = ""

    fun escape(printlnToEscape: String): EscapeBuilder {
        assert(printlnToEscape.isNotBlank())
        this.printlnToEscape = printlnToEscape.patternizeValue()
        return this
    }

    fun build(): String{
        val stringBuilder = StringBuilder()
        stringBuilder.append(" ESCAPE ")
        stringBuilder.append(this.printlnToEscape)
        return stringBuilder.toString()
    }
}