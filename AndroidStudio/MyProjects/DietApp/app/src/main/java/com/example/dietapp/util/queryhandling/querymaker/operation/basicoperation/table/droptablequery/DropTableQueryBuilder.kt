package com.example.dietapp.util.queryhandling.querymaker.operation.basicoperation.database.dropdatabasequery

import com.example.dietapp.util.queryhandling.querymaker.base.BaseQuery

/**
 * DropTableQueryBuilder
 *
 * extends BaseQuery
 *
 * @constructor A builder that create `Drop table` statement for table about MySQL
 */
class DropTableQueryBuilder(): BaseQuery() {
    override fun table(table: String): DropTableQueryBuilder {
        super.table(table)
        return this
    }

    override fun ifExists(ifExists: Boolean): DropTableQueryBuilder{
        super.ifExists(ifExists)
        return this
    }

    override fun build(): String{
        val stringBuilder = StringBuilder()
        stringBuilder.append("DROP ")
        stringBuilder.append(" TABLE ")
        if(this.ifExists){
            stringBuilder.append(" IF EXISTS ")
        }
        stringBuilder.append(this.tables[0])
        stringBuilder.append(" ;")
        return stringBuilder.toString()
    }
}