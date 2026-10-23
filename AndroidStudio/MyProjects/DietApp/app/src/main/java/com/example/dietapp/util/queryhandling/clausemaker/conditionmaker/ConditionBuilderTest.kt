package com.example.dietapp.util.queryhandling.clausemaker.conditionmaker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

object ConditionBuilderTest {
    @Composable
    fun test1(){
        Column {
            val conditionBuilder = ConditionBuilder()
            val query = conditionBuilder.key("key1").comparisonOperator(">=").value("10").build()
            Spacer(modifier = Modifier.height(10.dp).fillMaxWidth())
            Text(query)
        }
    }
}