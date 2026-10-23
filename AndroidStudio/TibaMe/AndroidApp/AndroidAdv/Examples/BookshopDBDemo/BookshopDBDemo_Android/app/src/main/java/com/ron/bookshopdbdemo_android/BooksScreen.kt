package com.ron.bookshopdbdemo_android

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ron.bookshopdbdemo_android.ui.theme.BookshopDemo_AndroidTheme

@Composable
fun BooksScreen(books: List<Book>, onItemClick: (Book) -> Unit) {
    var inputText by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = inputText,
                onValueChange = { inputText = it },
                // 在此placeholder較label(會將提示文字上移)適合
                placeholder = { Text(text = stringResource(id = R.string.book_name)) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search, contentDescription = "search"
                    )
                },
                trailingIcon = {
                    Icon(imageVector = Icons.Default.Clear,
                        contentDescription = "clear",
                        modifier = Modifier.clickable {
                            inputText = ""
                        })
                },
                maxLines = 1,
                shape = RoundedCornerShape(16.dp),
            )
            Spacer(modifier = Modifier.padding(start = 8.dp))
        }
        BookLists(books.filter { it.name.contains(inputText, true) },
            // 項目被點擊時執行
            onItemClick = {
                onItemClick(it)
            })
    }
}

/**
 * 列表內容
 * @param books 欲顯示的書籍清單
 * @param onItemClick 點擊列表項目時所需呼叫的函式
 */
@Composable
fun BookLists(
    books: List<Book>,
    onItemClick: (Book) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(books) { book ->
            // 用來建立Lists內容物
            ListItem(
                // 讓項目可被點擊，並設定點擊時欲執行內容
                modifier = Modifier.clickable {
                    onItemClick(book)
                },
                headlineContent = { Text(book.name) },
                supportingContent = { Text(book.price.toString()) },
            )
            HorizontalDivider()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BooksScreenPreview() {
    BookshopDemo_AndroidTheme {
        BooksScreen(books = listOf(Book("0001", "Android App",600.0)), onItemClick = {})
    }
}