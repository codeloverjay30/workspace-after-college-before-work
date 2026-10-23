package org.example.util.resultset

enum class RowAffectedInfoEnum(val value:Int){
    NO_ROW_AFFECTED(value = 0),
    EXCEPTION_AT_RUNTIME(value = -1),
    NO_SUCH_INFO(value = -2),
}