package com.tibame.tip101.group02.finalproject.healthhelper.util.querylanguagetomysqlstatement

object QueryLanguageToMySQLStatementHandlerTest {
    fun test(cases:List<String>):List<String> =
        cases.map{ case ->
            QueryLanguageToMySQLStatementHandler(case).convertQueryLanguageToMySQLWhereStatement()
        }
}