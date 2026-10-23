package com.jay30.focusrequesterdemo5

import android.widget.Toast
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext

@Composable
fun FocusRequesterDemo5Frame(){
    val context = LocalContext.current
    var text by remember { mutableStateOf("") }
    val focusRequester by remember {  mutableStateOf( FocusRequester() ) }
    LazyColumn {
        item {
            TextField(
                value = text,
                onValueChange = {
                    text = it
                    if (it.length > 3) {
                        focusRequester.captureFocus()
                        Toast.makeText(context,"focusRequester.captureFocus()\n was called.",Toast.LENGTH_SHORT).show()
                    } else {
                        focusRequester.freeFocus()
                        Toast.makeText(context,"focusRequester.freeFocus()\n was called.",Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .focusRequester(focusRequester)
            )
        }
    }
}