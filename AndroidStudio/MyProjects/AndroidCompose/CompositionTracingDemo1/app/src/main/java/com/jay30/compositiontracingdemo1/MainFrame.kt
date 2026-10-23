package com.jay30.compositiontracingdemo1

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MainFrame(){
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ){
        item{
            Text("Hello World!!!")
        }
        item{
            Text("Welcome to Android Studio!!!")
        }
    }
}