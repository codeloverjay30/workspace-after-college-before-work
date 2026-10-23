package com.ron.griddemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ron.griddemo.ui.theme.GridDemoTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GridDemoTheme {
                Main()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main() {
    // 設定內容向上捲動時，TopAppBar自動收起來；呼叫pinnedScrollBehavior()則不會收起來
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        // 設定則可追蹤捲動狀態，藉此調整畫面(例如內容向上捲動時，TopAppBar自動收起來)
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            // 建立small TopAppBar，預設為置左對齊
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text(text = "Books") },
                scrollBehavior = scrollBehavior
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { innerPadding ->
        // 一定要套用innerPadding，否則內容無法跟TopAppBar對齊
        BookGrid(fetchBooks(), innerPadding) { book ->
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
fun BookGrid(
    books: List<Book>,
    innerPadding: PaddingValues,
    onItemClick: (Book) -> Unit
) {
    LazyVerticalGrid(
        // 設定欄寬至少128dp
        columns = GridCells.Adaptive(minSize = 128.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        items(books) { book ->
            // 建立Image顯示圖片
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onItemClick(book) }
            ) {
                Image(
                    painter = painterResource(id = book.image),
                    contentDescription = "book's image",
                    modifier = Modifier.padding(top = 16.dp)
                )
                Text(text = book.name)
            }
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
fun GreetingPreview() {
    GridDemoTheme {
        Main()
    }
}