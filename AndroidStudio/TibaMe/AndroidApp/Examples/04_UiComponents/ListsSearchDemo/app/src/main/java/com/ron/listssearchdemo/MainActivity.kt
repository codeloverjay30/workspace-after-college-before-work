package com.ron.listssearchdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ron.listssearchdemo.ui.theme.ListsSearchDemoTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListsSearchDemoTheme {
                Main()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main() {
    var inputText by remember { mutableStateOf("") }
    // 書名含有使用者輸入的關鍵字者即保留
    val filteredBooks = fetchBooks().filter { it.name.contains(inputText, true) }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    OutlinedTextField(
                        value = inputText,
                        onValueChange = { inputText = it },
                        // 在此placeholder較label(會將提示文字上移)適合
                        placeholder = { Text(text = "book name") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "search"
                            )
                        },
                        // 文字方塊末端呈現清除圖示方便user清除文字
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "clear",
                                modifier = Modifier.clickable {
                                    inputText = ""
                                }
                            )
                        },
                        maxLines = 1,
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                },
                scrollBehavior = scrollBehavior
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { innerPadding ->
        // 每次搜尋結果變更都會使用List重新呈現
        BookLists(filteredBooks, innerPadding) { book ->
            scope.launch {
                snackbarHostState.showSnackbar(
                    "${book.name}, $${book.price}",
                    withDismissAction = true
                )
            }
        }
    }
}

/**
 * 列表內容
 * @param books 欲顯示的書籍清單
 * @param innerPadding 元件要套用innerPadding，否則內容無法跟TopAppBar對齊
 * @param onItemClick 點擊列表項目時所需呼叫的函式
 */
@Composable
fun BookLists(
    books: List<Book>,
    innerPadding: PaddingValues,
    onItemClick: (Book) -> Unit
) {
    // 也可改用LazyColumn
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            // 需要加上垂直捲動功能
            .verticalScroll(rememberScrollState())
    ) {
        books.forEach { book ->
            // 用來建立Lists內容物
            ListItem(
                modifier = Modifier.clickable {
                    onItemClick(book)
                },
                headlineContent = { Text(book.name) },
                supportingContent = { Text(book.price.toString()) },
                leadingContent = {
                    Image(
                        painter = painterResource(id = book.image),
                        contentDescription = "book"
                    )
                },
            )
            HorizontalDivider()
        }
    }
}

/**
 * 載入測試需要資料
 * @return 多本書資訊
 */
fun fetchBooks(): List<Book> {
    return listOf(
        Book("0001", "Android App", 600.0, R.drawable.android),
        Book("0002", "iOS App", 650.0, R.drawable.ios),
        Book("0003", "MySQL DB", 550.0, R.drawable.mysql),
        Book("0004", "Python", 500.0, R.drawable.python),
        Book("0005", "Kotlin", 520.0, R.drawable.kotlin),
        Book("0006", "MongoDB", 630.0, R.drawable.mongodb),
        Book("0007", "C/C++", 420.0, R.drawable.c_cplusplus),
        Book("0008", "C#", 480.0, R.drawable.csharp),
        Book("0009", "Swift", 540.0, R.drawable.swift),
        Book("0010", "Java", 520.0, R.drawable.java),
        Book("0011", "SQL Server", 620.0, R.drawable.sqlserver),
        Book("0012", "Oracle DB", 680.0, R.drawable.oracle),
        Book("0013", "HTML", 400.0, R.drawable.html),
        Book("0014", "Java Script", 470.0, R.drawable.javascript)
    )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ListsSearchDemoTheme {
        Main()
    }
}