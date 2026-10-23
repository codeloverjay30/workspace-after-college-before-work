package com.ron.bookscruddemo

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun BookListScreen(
    books: List<Book>,
    onAddButtonClick: () -> Unit,
    onEditButtonClick: (Book) -> Unit,
    onDeleteButtonClick: (Book) -> Unit
) {
    var inputText by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var showMore by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.padding(8.dp)) {
            OutlinedTextField(
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
            // 新增按鈕
            IconButton(
                onClick = onAddButtonClick
            ) {
                Icon(Icons.Filled.Add, contentDescription = "add")
            }

            // 異動按鈕(顯示/隱藏末端的編輯、刪除按鈕)
            IconButton(onClick = {
                showMore = !showMore
            }) {
                Icon(
                    Icons.Filled.MoreVert,
                    contentDescription = "edit and delete",
                )
            }
        }
        // 一定要套用innerPadding，否則內容無法跟TopAppBar對齊
        BookLists(books.filter { it.name.contains(inputText, true) },
            // 項目被點擊時執行
            onItemClick = {
                scope.launch {
                    snackbarHostState.showSnackbar(
                        "${it.name}, $${it.price}",
                        withDismissAction = true
                    )
                }
            },
            showMore,
            // 編輯按鈕被點擊時執行
            onEditClick = {
                onEditButtonClick(it)
            },
            // 刪除按鈕被點擊時執行
            onDeleteClick = {
                onDeleteButtonClick(it)
            })
    }
}

/**
 * 列表內容
 * @param books 欲顯示的書籍清單
 * @param onEditClick 點擊列表項目時所需呼叫的函式
 */
@Composable
fun BookLists(
    books: List<Book>,
    onItemClick: (Book) -> Unit,
    showMore: Boolean,
    onEditClick: (Book) -> Unit,
    onDeleteClick: (Book) -> Unit
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
                trailingContent = {
                    if (showMore) {
                        Row {
                            // 編輯按鈕
                            IconButton(onClick = {
                                onEditClick(book)
                            }) {
                                Icon(Icons.Filled.Edit, contentDescription = "edit")
                            }
                            // 刪除按鈕
                            IconButton(onClick = {
                                onDeleteClick(book)
                            }) {
                                Icon(Icons.Filled.Delete, contentDescription = "delete")
                            }
                        }
                    }
                }
            )
            HorizontalDivider()
        }
    }
}