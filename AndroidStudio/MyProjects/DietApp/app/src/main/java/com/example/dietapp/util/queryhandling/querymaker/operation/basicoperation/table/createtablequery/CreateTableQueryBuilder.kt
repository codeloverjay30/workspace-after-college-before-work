package com.example.dietapp.util.queryhandling.querymaker.operation.basicoperation.table.createtablequery

import com.example.dietapp.util.queryhandling.clausemaker.fieldsmaker.FieldsBuilder

/**
 * CreateTableQueryBuilder
 *
 * extends FieldsBuilder
 *
 * @constructor a builder that create `Create table` statement about MySQL
 */
class CreateTableQueryBuilder(): FieldsBuilder(){

    override fun build(): String {
        val fields = super.build()
        val stringBuilder = StringBuilder()
        stringBuilder.append("CREATE TABLE ")
        stringBuilder.append(" ( \n")
        stringBuilder.append(fields)
        stringBuilder.append(" \n)")
        stringBuilder.append(" ;")
        return stringBuilder.toString()
    }

    override fun toString(): String {
        return this.build()
    }
}