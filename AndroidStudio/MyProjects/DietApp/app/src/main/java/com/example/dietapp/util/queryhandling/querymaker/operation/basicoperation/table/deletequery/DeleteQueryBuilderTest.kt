package com.example.dietapp.util.queryhandling.querymaker.operation.basicoperation.table.deletequery

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dietapp.util.queryhandling.clausemaker.wheremaker.WhereBuilder
import com.example.dietapp.util.queryhandling.clausemaker.wheresmaker.WheresBuilder

object DeleteQueryBuilderTest {
    @Composable
    fun test1(){
        Column {
            val whereBuilder1 = WhereBuilder()
            val whereCondition1 = whereBuilder1.key("key1").comparisonOperator("=").value("value1").build()
            val whereBuilder2 = WhereBuilder()
            val whereCondition2 = whereBuilder2.key("key2").comparisonOperator("<=").value("value2").build()
            val wheresBuilder = WheresBuilder()
            val whereConditionQuery = wheresBuilder.condition(whereCondition1,whereCondition2).logicalOperator("AND").build()

            val queryBuilder = DeleteQueryBuilder()
            val query = queryBuilder
                .table("table1")
                .where(whereConditionQuery)
                .build()

            Spacer(modifier =  Modifier.height(10.dp).fillMaxWidth())
            Text(query)
        }
    }
}