package com.example.dietapp.util.queryhandling.querymaker.operation.basicoperation.database.showdatabasesquery

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

object ShowDatabasesQueryBuilderTest {
    @Composable
    fun test1(){
        Column {
            val queryBuilder = ShowDatabasesQueryBuilder()
            val query = queryBuilder.build()

            Text(query)
        }
    }
}