package com.tibame.tip101.exercise.exer6_02

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.sp
import com.tibame.tip101.exercise.exer6_02.ui.theme.Exer6_02Theme

@Composable
fun BookEditScreen(
    book: Book,
    onCancel: () -> Unit,
    onEdit: (Book) -> Unit
) {
    val isbn by remember { mutableStateOf(book.isbn) }
    var name by remember { mutableStateOf(book.name) }
    var price by remember { mutableStateOf(book.price.toString()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "ISBN: $isbn", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(text = stringResource(id = R.string.book_name)) }
        )
        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            label = { Text(text = stringResource(id = R.string.book_price)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                // 就將原本書內容替換成使用者輸入的新內容
                book.name = name
                book.price = price.toDoubleOrNull() ?: 0.0
                onEdit(book)
            }) {
                Text(text = stringResource(id = R.string.update_button))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = onCancel) {
                Text(text = stringResource(id = R.string.cancel_button))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookEditScreenPreview() {
    Exer6_02Theme {
        BookEditScreen(
            book = Book(isbn = "111", name = "MyBook", price = 600.0),
            onCancel = {},
            onEdit = { Book() }
        )
    }
}
