package com.tibame.tip101.group02.finalproject.healthhelper.util.queryhandling.querymaker.operation.basicoperation.database.usedatabasequery

import com.tibame.tip101.group02.finalproject.healthhelper.util.queryhandling.querymaker.base.BaseQuery

/**
 * UseDatabaseQueryBuilder
 *
 * extends FieldsBuilder
 *
 * @constructor A builder to create `Use database` statement to specify specific database about MySQL
 */
class UseDatabaseQueryBuilder(): BaseQuery() {

    override fun database(database: String): UseDatabaseQueryBuilder {
        super.database(database)
        return this
    }
    override fun build(): String{
        val stringBuilder = StringBuilder()
        stringBuilder.append("USE ")
        stringBuilder.append(this.database)
        stringBuilder.append(";")
        return stringBuilder.toString()
    }
}