package com.example.dietapp.util.queryhandling.clausemaker.orderbysmaker

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

object OrdersByBuilderTest {
    @Composable
    fun test1(){
        Column {
            val orderBysBuilder= OrderBysBuilder()
            val query = orderBysBuilder.orderBy("Columns1 ,DESC").build()

            Text(query)
        }
    }
}