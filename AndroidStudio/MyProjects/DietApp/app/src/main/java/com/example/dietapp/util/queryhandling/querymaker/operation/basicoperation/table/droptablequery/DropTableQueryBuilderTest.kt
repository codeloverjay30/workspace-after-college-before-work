package com.example.dietapp.util.queryhandling.querymaker.operation.basicoperation.database.dropdatabasequery

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

object DropTableQueryBuilderTest {
    @Composable
    fun test1(){
        Column {
            val queryBuilder = DropTableQueryBuilder()
            val query = queryBuilder.table("table1").build()

            Text(query)
        }
    }
}