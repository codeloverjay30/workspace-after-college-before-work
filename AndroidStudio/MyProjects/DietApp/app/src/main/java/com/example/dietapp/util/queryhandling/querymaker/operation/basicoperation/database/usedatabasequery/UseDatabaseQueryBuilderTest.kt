package com.example.dietapp.util.queryhandling.querymaker.operation.basicoperation.database.usedatabasequery

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

object UseDatabaseQueryBuilderTest {
    @Composable
    fun test1(){
        Column {
            val queryBuilder = UseDatabaseQueryBuilder()
            val query = queryBuilder.database("database1").build()

            Text(query)
        }
    }
}