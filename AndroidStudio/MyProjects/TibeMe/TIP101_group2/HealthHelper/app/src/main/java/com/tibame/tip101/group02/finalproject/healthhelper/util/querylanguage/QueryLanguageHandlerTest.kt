package com.tibame.tip101.group02.finalproject.healthhelper.util.querylanguage

object QueryLanguageHandlerTest {
    fun test(cases:List<String>): List<Map<String,String>> =
        cases.map{ QueryLanguageHandler.tryToHandleStatement(it) }
}