package org.example.util.sqlstatementtype

enum class SQLStatementTypeEnum(val value:String) {
    SELECT(value = "SELECT"),
    INSERT(value = "INSERT"),
    UPDATE(value = "UPDATE"),
    DELETE(value = "DELETE"),
}