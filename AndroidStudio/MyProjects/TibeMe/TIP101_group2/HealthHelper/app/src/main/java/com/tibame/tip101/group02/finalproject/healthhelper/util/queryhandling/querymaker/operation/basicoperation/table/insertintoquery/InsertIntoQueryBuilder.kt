package com.tibame.tip101.group02.finalproject.healthhelper.util.queryhandling.querymaker.operation.basicoperation.table.insertintoquery

import com.tibame.tip101.group02.finalproject.healthhelper.util.queryhandling.querymaker.base.BaseQuery

/**
 * InsertIntoQueryBuilder
 *
 * extends BaseQuery
 *
 * @constructor A builder that create `insert into` statement for a table about MySQL
 */
class InsertIntoQueryBuilder(): BaseQuery() {

    override fun from(vararg table: String): InsertIntoQueryBuilder {
        assert(table.size==1)
        super.from(*table)
        return this
    }

    override fun column(vararg column: String): InsertIntoQueryBuilder {
        super.column(*column)
        return this
    }

    override fun values(vararg value: String): InsertIntoQueryBuilder {
        super.values(*value)
        return this
    }

    override fun build(): String{
        this.assertWhenColumnSizeNotEqValueSize()

        val stringBuilder = StringBuilder()
        stringBuilder.append("INSERT ")

        stringBuilder.append(this.tables[0])

        stringBuilder.append(" INTO ")

        stringBuilder.append(" ( ")
        stringBuilder.append(this.columns[0])
        for(i in 1..this.columns.size-1){
            stringBuilder.append(" ,")
            stringBuilder.append(this.columns[i])
        }
        stringBuilder.append(" ) ")

        stringBuilder.append(" VALUES ")

        stringBuilder.append(" ( ")
        stringBuilder.append(this.values[0])
        for(i in 1..this.values.size-1){
            stringBuilder.append(" ,")
            stringBuilder.append(this.values[i])
        }
        stringBuilder.append(" ) ")

        stringBuilder.append(" ;")
        return stringBuilder.toString()
    }
}