package com.example.dietapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dietapp.result.queryresult.DietDiaryQueryResult

object DietDiaryQueryResultUI {
    @Composable
    fun show(){
        val items = DietDiaryQueryResult.queryResult
        Column{
            for(item in items){
                Card(
                    modifier = Modifier.height(80.dp).fillMaxWidth()
                ){
                    Text(item)
                }
            }
        }
    }
}