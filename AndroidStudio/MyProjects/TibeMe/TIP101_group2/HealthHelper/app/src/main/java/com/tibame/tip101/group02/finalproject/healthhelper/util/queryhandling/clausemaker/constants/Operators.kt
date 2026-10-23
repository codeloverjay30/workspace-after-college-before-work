package com.tibame.tip101.group02.finalproject.healthhelper.util.queryhandling.clausemaker.constants

data object Operators {
    data object LogicalOperator{
        val AND = "AND"
        val OR = "OR"
        val NOT = "NOT"
    }
    data object RangeOperator{
        val IN = "IN"
        val BETWEEN = "BETWEEN"
    }
    data object ComparisonOperator {
        val EQ = "="
        val NE = "!="
        val GT = ">"
        val LT = "<"
        val GE = ">="
        val LE = "<="
    }
}