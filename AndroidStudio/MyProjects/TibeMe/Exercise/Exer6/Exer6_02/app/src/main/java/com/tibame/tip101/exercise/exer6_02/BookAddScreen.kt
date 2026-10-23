package com.tibame.tip101.exercise.exer6_02

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tibame.tip101.exercise.exer6_02.ui.theme.Exer6_02Theme

@Composable
fun BookAddScreen(
    onCancel: () -> Unit,
    onAdd: (Book) -> Unit
) {
    var isbn by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = isbn,
            onValueChange = { isbn = it },
            label = { Text(text = stringResource(id = R.string.isbn)) },
            singleLine = true
        )
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(text = stringResource(id = R.string.book_name)) },
            singleLine = true
        )
        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            label = { Text(text = stringResource(id = R.string.book_price)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                val newBook = Book(
                    isbn, name, price.toDoubleOrNull() ?: 0.0,
                    android.R.drawable.ic_dialog_map
                )
                onAdd(newBook)

            }) {
                Text(stringResource(id = R.string.add_button))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = onCancel) {
                Text(stringResource(id = R.string.cancel_button))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookAddScreenPreview() {
    Exer6_02Theme {
        BookAddScreen(
            onCancel = {},
            onAdd = { Book() }
        )
    }
}