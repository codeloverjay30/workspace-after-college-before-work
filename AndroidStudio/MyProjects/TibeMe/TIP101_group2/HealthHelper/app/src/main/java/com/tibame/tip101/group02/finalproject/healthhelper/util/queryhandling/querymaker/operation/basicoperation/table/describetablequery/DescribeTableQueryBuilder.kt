package com.tibame.tip101.group02.finalproject.healthhelper.util.queryhandling.querymaker.operation.basicoperation.table.describetablequery

import com.tibame.tip101.group02.finalproject.healthhelper.util.queryhandling.querymaker.base.BaseQuery

/**
 * DescribeTableQueryBuilder
 *
 * extends BaseQuery
 *
 * @constructor A builder that create `Describe` statement for table about MySQL
 */
class DescribeTableQueryBuilder(): BaseQuery() {
    override fun table(table: String): DescribeTableQueryBuilder {
        super.table(table)
        return this
    }

    override fun build():String{
        val stringBuilder = StringBuilder()
        stringBuilder.append("DESCRIBE ")
        stringBuilder.append(this.tables[0])
        stringBuilder.append(" ;")
        return stringBuilder.toString()
    }
}