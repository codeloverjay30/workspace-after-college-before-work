package com.example.dietapp.util.queryhandling.clausemaker.patternmaker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

object PatternBuilderTest {
    @Composable
    fun test1(){
        Column {
            val queryBuilder = PatternBuilder()
            val query = queryBuilder.pattern("cat%").build()

            Spacer(modifier = Modifier.height(20.dp).fillMaxWidth())
            Text(query)
        }
    }
}