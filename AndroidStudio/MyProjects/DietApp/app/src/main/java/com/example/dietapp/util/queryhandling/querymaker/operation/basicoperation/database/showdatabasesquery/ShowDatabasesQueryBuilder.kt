package com.example.dietapp.util.queryhandling.querymaker.operation.basicoperation.database.showdatabasesquery

import com.example.dietapp.util.queryhandling.querymaker.base.BaseQuery

/**
 * ShowDatabasesQueryBuilder
 *
 * extends BaseQuery
 *
 * @constructor A builder that create `Show databases` statement to list all databases about MySQL
 */

class ShowDatabasesQueryBuilder(): BaseQuery() {

    override fun build():String{
        val stringBuilder = StringBuilder()
        stringBuilder.append("SHOW DATABASES ")
        stringBuilder.append(" ;")
        return stringBuilder.toString()
    }
}