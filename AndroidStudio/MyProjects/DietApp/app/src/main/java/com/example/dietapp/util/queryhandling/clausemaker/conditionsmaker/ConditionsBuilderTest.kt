package com.example.dietapp.util.queryhandling.clausemaker.conditionsmaker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dietapp.util.queryhandling.clausemaker.conditionmaker.ConditionBuilder

object ConditionsBuilderTest {
    @Composable
    fun test1(){
        Column {
            val conditionBuilder1 = ConditionBuilder()
            val condition1 = conditionBuilder1.key("key1").comparisonOperator("=").value("value1").build()
            val conditionBuilder2 = ConditionBuilder()
            val condition2 = conditionBuilder2.key("key2").comparisonOperator("<=").value("value2").build()
            val conditionsBuilder = ConditionsBuilder()
            val query = conditionsBuilder.condition(condition1,condition2).logicalOperator("AND").build()
            Spacer(modifier = Modifier.height(40.dp).fillMaxWidth())
            Text(query)
        }
    }
}