package com.example.dietapp.util.queryhandling.querymaker.operation.basicoperation.table.selectquery

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dietapp.util.queryhandling.clausemaker.havingmaker.HavingBuilder
import com.example.dietapp.util.queryhandling.clausemaker.havingsmaker.HavingsBuilder
import com.example.dietapp.util.queryhandling.clausemaker.wheremaker.WhereBuilder
import com.example.dietapp.util.queryhandling.clausemaker.wheresmaker.WheresBuilder

object SelectQueryBuilderTest {
    @Composable
    fun test1(){
        Column {
            val queryBuilder = SelectQueryBuilder()
            val query = queryBuilder.from("table1")
                .column("column1", "column2")
                .where("column1 = value1")
                .build()
            Spacer(modifier =  Modifier.height(10.dp).fillMaxWidth())
            Text(query)
        }
    }

    @Composable
    fun test2(){
        Column {
            val whereBuilder1 = WhereBuilder()
            val whereCondition1 = whereBuilder1.key("key1").comparisonOperator("=").value("value1").build()
            val whereBuilder2 = WhereBuilder()
            val whereCondition2 = whereBuilder2.key("key2").comparisonOperator("<=").value("value2").build()
            val wheresBuilder = WheresBuilder()
            val whereConditionQuery = wheresBuilder.condition(whereCondition1,whereCondition2).logicalOperator("AND").build()

            val havingBuilder1 = HavingBuilder()
            val havingConnection1 = havingBuilder1.key("key1").comparisonOperator("=").value("value1").build()
            val havingBuilder2 = HavingBuilder()
            val havingConnection2 = havingBuilder2.key("key2").comparisonOperator("<=").value("value2").build()
            val havingsBuilder = HavingsBuilder()
            val havingConditionQuery = havingsBuilder.condition(havingConnection1,havingConnection2).logicalOperator("AND").build()

            val queryBuilder = SelectQueryBuilder()
            val query = queryBuilder.from("table1")
                .column("column1", "column2")
                .where(whereConditionQuery)
                .having(havingConditionQuery)
                .limit(1u)
                .offset(2u)
                .build()
            Spacer(modifier =  Modifier.height(10.dp).fillMaxWidth())
            Text(query)
        }
    }
}