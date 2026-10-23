package com.jay30.focusdemo1

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun FocusDemo1Frame() {
    Scaffold(
        content = { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                item {
                    LazyRow {
                        item {
                            TextButton({ }) { Text("First field") }
                            TextButton({ }) { Text("Second field") }
                        }
                    }

                    LazyRow {
                        item {
                            TextButton({ }) { Text("Third field") }
                            TextButton({ }) { Text("Fourth field") }
                        }
                    }
                }
            }
        }
    )
}