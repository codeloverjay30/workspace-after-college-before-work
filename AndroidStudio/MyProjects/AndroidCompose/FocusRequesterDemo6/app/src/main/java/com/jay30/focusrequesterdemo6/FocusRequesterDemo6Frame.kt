package com.jay30.focusrequesterdemo6

import android.widget.Toast
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusDirection.Companion.Down
import androidx.compose.ui.focus.FocusDirection.Companion.Right
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FocusRequesterDemo6Frame(){
    val context = LocalContext.current
    var text by remember { mutableStateOf("") }
    val focusRequester by remember {  mutableStateOf( FocusRequester() ) }
    LazyColumn {
        item {
            TextField(
                value = text,
                onValueChange = {
                    text = it
                },
                modifier = Modifier.focusProperties {
                    exit = { focusDirection ->
                        when (focusDirection) {
                            FocusDirection.Companion.Right -> FocusRequester.Cancel
                            FocusDirection.Companion.Down -> focusRequester
                            else -> FocusRequester.Default
                        }
                    }
                }
            )
        }
    }
}