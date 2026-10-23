package com.example.dietapp.util.queryhandling.querymaker.operation.basicoperation.table.showtablesquery

import com.example.dietapp.util.queryhandling.querymaker.base.BaseQuery

/**
 * ShowTablesQueryBuilder
 *
 * extends BaseQuery
 *
 * @constructor A builder that create `Show tables` statement about MySQL
 */

class ShowTablesQueryBuilder(): BaseQuery() {

    override fun build():String{
        val stringBuilder = StringBuilder()
        stringBuilder.append("SHOW TABLES ")
        stringBuilder.append(" ;")
        return stringBuilder.toString()
    }
}