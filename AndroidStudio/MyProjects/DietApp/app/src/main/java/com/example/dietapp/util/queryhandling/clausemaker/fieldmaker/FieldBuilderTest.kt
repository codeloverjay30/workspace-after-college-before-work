package com.example.dietapp.util.queryhandling.clausemaker.fieldmaker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

object FieldBuilderTest {
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

            Spacer(modifier =  Modifier.height(20.dp).fillMaxWidth())
            Text(field1)
        }
    }
}