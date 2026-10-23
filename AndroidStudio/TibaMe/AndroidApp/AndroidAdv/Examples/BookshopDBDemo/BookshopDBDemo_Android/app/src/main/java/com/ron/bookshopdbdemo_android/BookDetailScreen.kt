package com.ron.bookshopdbdemo_android

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ron.bookshopdbdemo_android.ui.theme.BookshopDemo_AndroidTheme

@Composable
fun BookDetailScreen(book: Book) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        Log.d("BookDetailScreen", "book: $book")

        Text(text = "ISBN: ${book.isbn}", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Name: ${book.name}", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Price: ${book.price}", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Author: ${book.author}", fontSize = 24.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun BookEditScreenPreview() {
    BookshopDemo_AndroidTheme {
        BookDetailScreen(Book("0001", "Android App", 600.0, author = "Allen"))
    }
}
