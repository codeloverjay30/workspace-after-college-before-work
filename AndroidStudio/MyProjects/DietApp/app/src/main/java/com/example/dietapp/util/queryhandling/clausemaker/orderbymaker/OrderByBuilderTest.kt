package com.example.dietapp.util.queryhandling.clausemaker.orderbymaker

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

object OrderByBuilderTest {
    @Composable
    fun test1(){
        Column {
            val orderByBuilder = OrderByBuilder()
            val query= orderByBuilder.column("colum1").orderWay(" ASC ").build()
            Text(query)
        }
    }
}