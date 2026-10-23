package com.ron.ex06_2

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ron.ex06_2.ui.theme.Ex06_2Theme

/**
 * 建立列舉類型，其屬性代表各頁面
 */
enum class Screen(@StringRes val title: Int) {
    BookList(title = R.string.book_list),
    BookDetail(title = R.string.book_detail),
    Help(title = R.string.help)
}

/**
 * Main是一個頁面容器，其他頁面會依照使用者操作被加上來
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main(
    bookViewModel: BookViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    // 取得儲存在back stack最上層的頁面
    val backStackEntry by navController.currentBackStackEntryAsState()
    // 取得當前頁面的名稱
    val currentScreen = Screen.valueOf(
        // destination是目前顯示的頁面，若為null則設定BookList這頁為目前顯示的頁面
        backStackEntry?.destination?.route ?: Screen.BookList.name
    )
    // 從StateFlow取得最新資料
    val books by bookViewModel.booksState.collectAsState()
    val bookDetail by bookViewModel.bookDetailState.collectAsState()
    // 設定內容向上捲動時，TopAppBar自動收起來；呼叫pinnedScrollBehavior()則不會收起來
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    Scaffold(
        // 設定則可追蹤捲動狀態，藉此調整畫面(例如內容向上捲動時，TopAppBar自動收起來)
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MainAppBar(
                navController = navController,
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.BookList.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)

        ) {
            composable(route = Screen.BookList.name) {
                /* 在composable階層架構中，上層建立的物件傳給下一層，基本上是安全的。
                所以可將navController或ViewModel物件傳給下一個Composable。
                但若不將navController與ViewModel物件傳給BookListScreen，就需要靠callback函式。 */
                BookListScreen(
                    books = books,
                    onItemClick = {
                        bookViewModel.setDetailBook(it)
                        navController.navigate(Screen.BookDetail.name)
                    },
                )
            }
            // 設定指定的路徑(route)會到指定的畫面(screen)
            composable(route = Screen.BookDetail.name) {
                BookDetailScreen(
                    navController = navController,
                    book = bookDetail
                )
            }
            composable(
                route = Screen.Help.name
            ) {
                HelpScreen()
            }
        }
    }
}

/**
 * 建立topBar與回上頁按鈕
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppBar(
    navController: NavHostController,
    currentScreen: Screen,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior
) {
    TopAppBar(
        // 設定頁面標題
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            // 如果可回上頁，就顯示Back按鈕
            if (canNavigateBack) {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        },
        actions = {
            // 點擊"i"圖示按鈕會開啟"Help"頁面
            IconButton(onClick = {
                navController.navigate(Screen.Help.name)
            }) {
                Icon(imageVector = Icons.Filled.Info, contentDescription = "Info")
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    Ex06_2Theme {
        Main()
    }
}