package com.tibame.tip101.group02.finalproject.healthhelper.util.queryhandling.clausemaker.fieldsmaker

/**
 * FieldsBuilder
 *
 * @constructor A builder that create a list of field clause about MySQL.
 */
open class FieldsBuilder {
    private var fields: String = ""

    fun fields(vararg fields: String): FieldsBuilder {
        this.fields = ""
        this.fields += fields[0]
        for(i in 1..fields.size-1){
            this.fields += " ,\n"
            this.fields += fields[i]
        }
        return this
    }

    fun fields(fields: MutableList<String>): FieldsBuilder {
        this.fields = ""
        this.fields += fields[0]
        for(i in 1..fields.size-1){
            this.fields += " ,\n"
            this.fields += fields[i]
        }
        return this
    }

    fun addFields(vararg fields:String): FieldsBuilder {
        if(this.fields.isNotEmpty()){
            this.fields += " ,\n"
        }
        this.fields += fields[0]
        for(i in 1..<fields.size){
            this.fields += " ,\n"
            this.fields += fields[i]
        }
        return this
    }

    fun addFields(fields:MutableList<String>): FieldsBuilder {
        if(this.fields.isNotEmpty()){
            this.fields += " ,\n"
        }
        this.fields += fields[0]
        for(i in 1..<fields.size){
            this.fields += " ,\n"
            this.fields += fields[i]
        }
        return this
    }

    open fun build(): String{
        assert(this.fields.isNotEmpty())
        return this.fields
    }
}