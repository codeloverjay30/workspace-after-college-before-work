package com.example.dietapp.util.queryhandling.clausemaker.fieldmaker

/**
 * FieldBuilder
 *
 * @constructor A builder that create a field clause about MySQL.
 */
class FieldBuilder {
    private var fieldName: String = ""
    private var fieldType: String = ""
    private var notNull: Boolean = false
    private var primaryKey: Boolean = false
    private var foreignKey: Boolean = false
    private var foreignKeyTable: String = ""
    private var foreignKeyField: String = ""
    private var autoIncrement: Boolean = false

    fun fieldName(fieldName:String): FieldBuilder{
        assert(fieldName.isNotBlank())
        this.fieldName = fieldName.lowercase()
        return this
    }

    fun fieldType(fieldType:String): FieldBuilder{
        assert(fieldType.isNotBlank())
        this.fieldType = fieldType.uppercase()
        return this
    }

    fun notNull(notNull:Boolean = true): FieldBuilder{
        this.notNull = notNull
        return this
    }

    fun primaryKey(primaryKey:Boolean = true): FieldBuilder{
        this.primaryKey = primaryKey
        return this
    }

    fun foreignKey(foreignKey:Boolean = true): FieldBuilder{
        this.foreignKey = foreignKey
        return this
    }

    fun foreignKeyTable(foreignKeyTable:String): FieldBuilder{
        assert(foreignKeyTable.isNotBlank())
        this.foreignKeyTable = foreignKeyTable.lowercase()
        return this
    }

    fun foreignKeyField(foreignKeyField:String): FieldBuilder{
        assert(foreignKeyField.isNotBlank())
        this.foreignKeyField = foreignKeyField.lowercase()
        return this
    }

    fun autoIncrement(autoIncrement:Boolean = true): FieldBuilder{
        this.autoIncrement = autoIncrement
        return this
    }

    fun build(): String{
        assert(this.fieldName.isNotBlank())
        assert(this.fieldType.isNotBlank())

        val stringBuilder = StringBuilder()
        stringBuilder.append(this.fieldName)
        stringBuilder.append(" ")
        stringBuilder.append(this.fieldType)
        if(this.notNull){
            stringBuilder.append(" NOT NULL ")
        }
        if(this.primaryKey){
            stringBuilder.append(" PRIMARY KEY ")
        }
        if(this.foreignKey){
            stringBuilder.append(" FOREIGN KEY ")
            stringBuilder.append(" ( ")
            stringBuilder.append(this.foreignKeyTable)
            stringBuilder.append(".")
            stringBuilder.append(this.foreignKeyField)
            stringBuilder.append(" ) ")
        }
        if(this.autoIncrement){
            stringBuilder.append(" AUTO_INCREMENT ")
        }
        return stringBuilder.toString()
    }

    override fun toString(): String {
        return this.build()
    }

}