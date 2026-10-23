package com.jay30.focusdemo2

import androidx.compose.foundation.layout.Arrangement
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
fun FocusDemo2Frame(){
    Scaffold(
        content = { innerPadding ->
            LazyRow(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalArrangement = Arrangement.Center,
            ) {
                item {
                    LazyColumn {
                        item {
                            TextButton({ }) { Text("First field") }
                            TextButton({ }) { Text("Second field") }
                        }
                    }

                    LazyColumn {
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