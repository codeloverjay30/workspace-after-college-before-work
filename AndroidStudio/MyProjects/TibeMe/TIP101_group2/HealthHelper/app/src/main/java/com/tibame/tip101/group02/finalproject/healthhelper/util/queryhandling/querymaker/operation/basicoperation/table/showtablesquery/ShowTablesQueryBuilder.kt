package util.queryhandling.querymaker.operation.basicoperation.table.showtablesquery

import com.tibame.tip101.group02.finalproject.healthhelper.util.queryhandling.querymaker.base.BaseQuery

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