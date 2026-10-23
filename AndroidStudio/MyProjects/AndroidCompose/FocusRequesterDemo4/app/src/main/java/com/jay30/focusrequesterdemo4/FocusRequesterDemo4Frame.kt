package com.jay30.focusrequesterdemo4

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester

@Composable
fun FocusRequesterDemo4Frame(){
    val focusRequester = remember { FocusRequester() }
    var text by remember { mutableStateOf("") }

    LazyColumn {

        item {
            TextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier
                    .focusRequester(focusRequester)
            )

            Button(onClick = { focusRequester.requestFocus() }) {
                Text("Request focus on TextField")
            }
        }
    }
}