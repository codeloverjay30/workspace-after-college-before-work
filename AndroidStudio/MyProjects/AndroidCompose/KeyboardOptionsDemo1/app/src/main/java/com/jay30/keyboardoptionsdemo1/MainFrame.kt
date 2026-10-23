package com.jay30.keyboardoptionsdemo1

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun MainFrame() {
    var text by remember { mutableStateOf("") }
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ){
        item {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = text,
                onValueChange = { text = it },
                label = { Text("Keyboard Cheat Sheet") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password, // Change keyboard Type here
                    imeAction = ImeAction.Send // Change Action Here
                )
            )
        }
    }
}