package com.tibame.tip101.group02.finalproject.healthhelper.util.queryhandling.clausemaker.orderbymaker

/**
 * OrderByBuilder
 *
 * @constructor A builder that create a part of `ORDER BY` clause about MySQL.
 */

class OrderByBuilder {
    private var column : String = ""
    private var orderWay : String = ""

    fun column(column:String): OrderByBuilder {
        this.column = column
        return this
    }

    fun orderWay(orderWay:String): OrderByBuilder {
        this.orderWay = orderWay
        return this
    }

    fun build(): String{
        assert(this.column.isNotBlank())
        assert(this.orderWay.isNotBlank())

        val stringBuilder = StringBuilder()
        stringBuilder.append(this.column)
        stringBuilder.append(" ")
        stringBuilder.append(this.orderWay)

        return stringBuilder.toString()
    }
}