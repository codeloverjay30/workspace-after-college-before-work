package org.example.util.preparedstatement

import org.example.util.resultset.ResultSetInfo
import org.example.util.sqlstatementtype.SQLStatementTypeEnum
import java.sql.Connection
import java.sql.PreparedStatement

class PreparedStatementHandler(
    val connection: Connection,
    val sqlCommand: String,
    val sqlOption: Int? = null,
){
    val argumentsCount: Int
        get() = sqlCommand.split("?").size

    val preparedStatement: PreparedStatement = if(this.sqlOption==null)
            connection.prepareStatement(this.sqlCommand)
        else
            connection.prepareStatement(this.sqlCommand,this.sqlOption)

    var resultSetInfo: ResultSetInfo = ResultSetInfo()

    fun fillInByIndex(
        arguments: List<Any?>,
    ): PreparedStatement{
        assert(arguments.size <= argumentsCount,{ "Too many arguments to fill into prepared statement" } )
        arguments.forEachIndexed{ index, arg ->
            this.preparedStatement.setObject(index + 1, arg)
        }
        return this.preparedStatement
    }

    fun getStatementType():Int{
        val trimmedSqlCommand = this.sqlCommand.trimStart{ it == ' ' }

        if(trimmedSqlCommand.startsWith(SQLStatementTypeEnum.SELECT.value)){
            return 1
        }else if(trimmedSqlCommand.startsWith(SQLStatementTypeEnum.DELETE.value)){
            return 0
        }else if(trimmedSqlCommand.startsWith(SQLStatementTypeEnum.INSERT.value)){
            return 0
        }else if(trimmedSqlCommand.startsWith(SQLStatementTypeEnum.UPDATE.value)){
            return 0
        }else{
            return -1
        }
    }
    fun execute(){
        if(this.getStatementType() == 1){
            val resultSet = this.preparedStatement.executeQuery()
            this.resultSetInfo.resultSet = resultSet
            this.resultSetInfo.affectedRowCount = resultSet.metaData.columnCount
        }else{
            val affectedRowCount = this.preparedStatement.executeUpdate()
            this.resultSetInfo.affectedRowCount = affectedRowCount
            this.resultSetInfo.resultSet = null
        }
    }
}