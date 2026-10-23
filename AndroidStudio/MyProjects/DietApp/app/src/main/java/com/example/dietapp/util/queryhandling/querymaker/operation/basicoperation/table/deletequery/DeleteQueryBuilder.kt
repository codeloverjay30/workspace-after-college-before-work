package com.example.dietapp.util.queryhandling.querymaker.operation.basicoperation.table.deletequery

import com.example.dietapp.util.queryhandling.querymaker.base.BaseQuery

/**
 * DeleteQueryBuilder
 *
 * extends BaseQuery
 *
 * @constructor A builder to create `delete` statement for table about MySQL
 */
class DeleteQueryBuilder(): BaseQuery(){

    override fun table(table: String): DeleteQueryBuilder {
        super.table(table)
        return this
    }

    override fun where(condition: String): DeleteQueryBuilder {
        super.where(condition)
        return this
    }

    override fun build(): String{
        val stringBuilder = StringBuilder()

        stringBuilder.append(" DELETE ")
        stringBuilder.append(" FROM ")
        stringBuilder.append(this.tables[0])
        for (i in 1..this.tables.size-1) {
            stringBuilder.append(", ")
            stringBuilder.append(this.tables[i])
        }

        if(this.condition.trim().isNotBlank()){
            stringBuilder.append(" WHERE ")
            stringBuilder.append(this.condition)
        }

        stringBuilder.append(" ;")
        return stringBuilder.toString()
    }
}