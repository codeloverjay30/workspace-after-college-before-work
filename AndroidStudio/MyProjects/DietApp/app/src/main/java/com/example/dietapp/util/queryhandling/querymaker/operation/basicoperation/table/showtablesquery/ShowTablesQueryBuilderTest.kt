package com.example.dietapp.util.queryhandling.querymaker.operation.basicoperation.table.showtablesquery

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

object ShowTablesQueryBuilderTest {
    @Composable
    fun test1(){
        Column {
            val queryBuilder = ShowTablesQueryBuilder()
            val query = queryBuilder.build()

            Text(query)
        }
    }
}