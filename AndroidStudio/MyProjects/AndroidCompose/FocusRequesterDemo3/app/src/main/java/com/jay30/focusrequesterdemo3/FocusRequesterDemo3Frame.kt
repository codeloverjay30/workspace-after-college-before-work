package com.jay30.focusrequesterdemo3

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FocusRequesterDemo3Frame() {
    val (first, second, third, fourth) = remember { FocusRequester.createRefs() }
    LazyColumn {
        item {
            LazyRow {
                item {
                    TextButton(
                        onClick = {},
                        modifier = Modifier
                            .focusRequester(first)
                            .focusProperties {
                                left = third
                                up = second
                                down = second
                                right = third
                            }
                    ) {
                        Text("First Field")
                    }
                    TextButton(
                        onClick = {},
                        modifier = Modifier
                            .focusRequester(third)
                            .focusProperties {
                                left = first
                                up = fourth
                                down = fourth
                                right = first
                            }
                    ) {
                        Text("Third Field")
                    }
                }
            }

            LazyRow {
                item {
                    TextButton(
                        onClick = {},
                        modifier = Modifier
                            .focusRequester(second)
                            .focusProperties {
                                left = fourth
                                up = first
                                down = first
                                right = fourth
                            }
                    ) {
                        Text("Second Field")
                    }
                    TextButton(
                        onClick = {},
                        modifier = Modifier
                            .focusRequester(fourth)
                            .focusProperties {
                                left = second
                                up = third
                                down = third
                                right = second
                            }
                    ) {
                        Text("Fourth Field")
                    }
                }
            }
        }
    }
}