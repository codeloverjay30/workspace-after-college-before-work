package org.example.util.resultset

import org.example.util.classhandler.ClassHandler
import java.sql.ResultSet

class BaseResultSetHandler<C:Class<*>>(
    val resultSet: ResultSet,
    val daoClass:C,
){
    val classHandler = ClassHandler(daoClass)
    val daoClassFieldsName = classHandler.getFieldsName()

    val listOfCls = mutableListOf<C>()

    fun getData(){
        this.listOfCls.clear()

        while(resultSet.next()){
            val currentFieldsValue = mutableListOf<String>()
            this.daoClassFieldsName.forEach{ fieldName ->

            }
        }
    }
}