package com.example.dietapp.util.queryhandling.clausemaker.patternmaker

import com.example.dietapp.extension.string.patternizeValue

/**
 * PatternBuilder
 *
 * @constructor A builder that create a `PATTERN` clause about MySQL.
 */

class PatternBuilder {
    private var pattern: String = ""

    fun pattern(pattern: String): PatternBuilder {
        assert(pattern.isNotBlank())
        this.pattern = pattern.patternizeValue()
        return this
    }

    fun build(): String{
        val stringBuilder = StringBuilder()
        stringBuilder.append(" LIKE ")
        stringBuilder.append(pattern)
        return stringBuilder.toString()
    }
}