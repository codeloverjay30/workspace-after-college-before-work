package com.tibame.tip101.group02.finalproject.healthhelper.util.queryhandling.clausemaker.orderbysmaker

/**
 * OrderBysBuilder
 *
 * @constructor A builder that create a `ORDER BY` clause about MySQL.
 */

class OrderBysBuilder {
    private val orderBys : MutableList<String> = ArrayList()

    fun orderBy(vararg orderBy:String): OrderBysBuilder {
        this.orderBys.addAll(orderBy)
        return this
    }

    fun orderBys(orderBys : MutableList<String>): OrderBysBuilder {
        this.orderBys.addAll(orderBys)
        return this
    }

    fun build():String{
        assert(this.orderBys.isNotEmpty())

        val stringBuilder = StringBuilder()

        stringBuilder.append(this.orderBys)
        for(i in 1..<this.orderBys.size){
            stringBuilder.append(", ")
            stringBuilder.append(this.orderBys[i])
        }

        return stringBuilder.toString()
    }
}