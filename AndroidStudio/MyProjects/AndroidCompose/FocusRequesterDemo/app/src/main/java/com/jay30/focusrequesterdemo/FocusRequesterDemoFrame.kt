package com.jay30.focusrequesterdemo

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FocusRequesterDemoFrame(){
    val (first, second, third, fourth) = remember { FocusRequester.createRefs() }
    LazyColumn  {
        item {
            LazyRow {
                item {
                    TextButton({}, Modifier.focusRequester(first)) { Text("First field") }
                    TextButton({}, Modifier.focusRequester(third)) { Text("Third field") }
                }
            }

            LazyRow {
                item {
                    TextButton({}, Modifier.focusRequester(second)) { Text("Second field") }
                    TextButton({}, Modifier.focusRequester(fourth)) { Text("Fourth field") }
                }
            }
        }
    }
}