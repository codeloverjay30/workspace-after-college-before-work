package com.ron.ex06_2

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun BookListScreen(
    books: List<Book>,
    onItemClick: (Book) -> Unit,
) {
    var inputText by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier
            .padding(8.dp)) {
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
        }
        // 一定要套用innerPadding，否則內容無法跟TopAppBar對齊
        BookLists(books.filter { it.name.contains(inputText, true) },
            // 編輯按鈕被點擊時執行
            onItemClick = {
                onItemClick(it)
            })
    }
}

/**
 * 列表內容
 * @param books 欲顯示的書籍清單
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
                leadingContent = {
                    Image(
                        painter = painterResource(id = book.image), contentDescription = "book"
                    )
                },
            )
            Divider()
        }
    }
}