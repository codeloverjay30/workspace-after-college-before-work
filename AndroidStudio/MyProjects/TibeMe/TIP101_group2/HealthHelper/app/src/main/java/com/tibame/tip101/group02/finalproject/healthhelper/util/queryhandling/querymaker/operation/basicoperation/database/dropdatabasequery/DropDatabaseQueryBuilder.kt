package com.tibame.tip101.group02.finalproject.healthhelper.util.queryhandling.querymaker.operation.basicoperation.database.dropdatabasequery

import com.tibame.tip101.group02.finalproject.healthhelper.util.queryhandling.querymaker.base.BaseQuery

/**
 * DropDatabaseQueryBuilder
 *
 * extends BaseQuery
 *
 * @constructor A builder that create `Drop database` statement to drop a database about MySQL
 */

class DropDatabaseQueryBuilder(): BaseQuery() {
    override fun database(database: String): DropDatabaseQueryBuilder {
        super.database(database)
        return this
    }

    override fun ifExists(ifExists: Boolean): DropDatabaseQueryBuilder {
        super.ifExists(ifExists)
        return this
    }

    override fun build(): String{
        val stringBuilder = StringBuilder()
        stringBuilder.append("DROP ")
        stringBuilder.append(" DATABASE ")
        if(this.ifExists){
            stringBuilder.append(" IF EXISTS ")
        }
        stringBuilder.append(this.database)
        stringBuilder.append(" ;")
        return stringBuilder.toString()
    }
}