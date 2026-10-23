package com.example.dietapp.util.queryhandling.querymaker.operation.basicoperation.table.updatequery

import com.example.dietapp.util.queryhandling.querymaker.base.BaseQuery

/**
 * UpdateQueryBuilder
 *
 * extends BaseQuery
 *
 * @constructor A builder that create `UPDATE ... SET` statement for table about MySQL
 */
class UpdateQueryBuilder():BaseQuery() {

    override fun from(vararg table: String): UpdateQueryBuilder {
        assert(table.size == 1)
        super.from(*table)
        return this
    }

    override fun column(vararg column: String): UpdateQueryBuilder {
        super.column(*column)
        return this
    }

    override fun values(vararg value: String): UpdateQueryBuilder {
        super.values(*value)
        return this
    }

    override fun where(condition: String): UpdateQueryBuilder {
        super.where(condition)
        return this
    }

    override fun build(): String{
        assertWhenColumnSizeNotEqValueSize()

        val stringBuilder = StringBuilder()
        stringBuilder.append("UPDATE ")

        stringBuilder.append(this.tables[0])

        stringBuilder.append(" SET ")

        stringBuilder.append(this.columns[0])
        stringBuilder.append(" = ")
        stringBuilder.append(this.values[0])

        for(i in 1..this.columns.size-1){
            stringBuilder.append(" ,")
            stringBuilder.append(this.columns[i])
            stringBuilder.append(" = ")
            stringBuilder.append(this.values[i])
        }

        if(this.condition.isNotBlank()){
            stringBuilder.append(" WHERE ")
            stringBuilder.append(this.condition)
        }

        stringBuilder.append(" ;")
        return stringBuilder.toString()
    }
}