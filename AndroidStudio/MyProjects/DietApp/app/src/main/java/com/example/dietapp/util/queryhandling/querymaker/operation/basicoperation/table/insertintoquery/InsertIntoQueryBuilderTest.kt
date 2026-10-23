package com.example.dietapp.util.queryhandling.querymaker.operation.basicoperation.table.insertintoquery

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

object InsertIntoQueryBuilderTest {
    @Composable
    fun test1(){
        Column {
            val insertIntoQueryBuilder = InsertIntoQueryBuilder()
            val query = insertIntoQueryBuilder
                .from("table1")
                .column("column1","column2")
                .values("value1","value2")
                .build()

            Text(query)
        }
    }

    @Composable
    fun test2(){
        Column {
            val insertIntoQueryBuilder = InsertIntoQueryBuilder()
            val query = insertIntoQueryBuilder
                .from("table1")
                .column("column1")
                .values("value1","value2")
                .build()

            Text(query)
        }
    }
}