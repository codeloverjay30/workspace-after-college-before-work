package com.tibame.tip101.group02.finalproject.healthhelper.util.querylanguagetomysqlstatement

object QueryLanguageToMySQLStatementHandlerTestCases {
    fun test():String{
        val cases = listOf(
            "key1:value1",
            "key2:value2",
            "key1:value1&key2:value2",
            "key:value&ke:value2",
            "key:value&key:value2",
            ":value&key:value2",
            "key:el:value&key:value2",
            "",
        )

        val results = QueryLanguageToMySQLStatementHandlerTest.test(cases)

        return results.joinToString { "\n" }
    }
}