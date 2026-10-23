package com.jay30.composablenavigationdemo

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun SexyWebsite(){
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ){
        item{
            Text(stringResource(R.string.welcome_message, stringResource(R.string.sexy_store)))
        }
    }
}