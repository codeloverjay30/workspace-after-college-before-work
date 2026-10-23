package com.example.dietapp.util.queryhandling.querymaker.operation.basicoperation.table.describetablequery

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

object DescribeTableQueryBuilderTest {
    @Composable
    fun test1(){
        Column {
            val queryBuilder = DescribeTableQueryBuilder()
            val query = queryBuilder
                .table("table1")
                .build()

            Text(query)
        }
    }
}