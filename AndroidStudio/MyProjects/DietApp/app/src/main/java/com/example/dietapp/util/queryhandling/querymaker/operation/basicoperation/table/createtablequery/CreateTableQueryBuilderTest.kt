package com.example.dietapp.util.queryhandling.querymaker.operation.basicoperation.table.createtablequery

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dietapp.util.queryhandling.clausemaker.fieldmaker.FieldBuilder
import com.example.dietapp.util.queryhandling.clausemaker.fieldsmaker.FieldsBuilder

object CreateTableQueryBuilderTest {
    @Composable
    fun test1(){
        Column {
            val fieldBuilder1 = FieldBuilder()
            val field1 = fieldBuilder1
                .fieldName("field1")
                .fieldType("int")
                .primaryKey(false)
                .foreignKey(false)
                .foreignKeyTable("table1")
                .foreignKeyField("otherField1")
                .notNull(true)
                .build()

            val fieldBuilder2 = FieldBuilder()
            val field2 = fieldBuilder2
                .fieldName("field2")
                .fieldType("varchar(100)")
                .primaryKey(false)
                .foreignKey(true)
                .foreignKeyTable("table2")
                .foreignKeyField("otherField2")
                .notNull(true)
                .build()

            val fieldBuilder3 = FieldBuilder()
            val field3 = fieldBuilder3
                .fieldName("field3")
                .fieldType("varchar(100)")
                .primaryKey(true)
                .foreignKey(true)
                .foreignKeyTable("table3")
                .foreignKeyField("otherField3")
                .notNull(true)
                .build()

            val queryBuilder = CreateTableQueryBuilder()
            val query = queryBuilder
                .addFields(field1,field2,field3)
                .build()

            Spacer(modifier =  Modifier.height(20.dp).fillMaxWidth())
            Text(query)
        }
    }
}