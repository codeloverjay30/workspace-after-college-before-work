package com.example.dietapp.util.queryhandling.querymaker.operation.basicoperation.table.updatequery

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.dietapp.util.queryhandling.clausemaker.wheremaker.WhereBuilder
import com.example.dietapp.util.queryhandling.clausemaker.wheresmaker.WheresBuilder

object UpdateQueryBuilderTest {
    @Composable
    fun test1(){
        Column {
            val whereBuilder1 = WhereBuilder()
            val whereCondition1 = whereBuilder1.key("key1").comparisonOperator("=").value("value1").build()
            val whereBuilder2 = WhereBuilder()
            val whereCondition2 = whereBuilder2.key("key2").comparisonOperator("<=").value("value2").build()
            val wheresBuilder = WheresBuilder()
            val whereConditionQuery = wheresBuilder.condition(whereCondition1,whereCondition2).logicalOperator("AND").build()

            val queryBuilder = UpdateQueryBuilder()
            val query = queryBuilder
                .from("table1")
                .column("column1")
                .values("value")
                .where(whereConditionQuery)
                .build()

            Text(query)
        }
    }
}