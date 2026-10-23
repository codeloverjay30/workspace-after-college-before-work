package com.ron.bookscruddemo

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ron.bookscruddemo.ui.theme.BooksCRUDDemoTheme
import kotlinx.coroutines.launch

/**
 * 建立列舉類型，其屬性代表各頁面
 */
enum class Screen(@StringRes val title: Int) {
    BookList(title = R.string.book_list),
    BookAdd(title = R.string.book_add),
    BookEdit(title = R.string.book_edit)
}

/**
 * Main是一個頁面容器，其他頁面會依照使用者操作適時地被加上來
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main(
    bookViewModel: BookViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val context = LocalContext.current
    // 取得儲存在back stack最上層的頁面
    val backStackEntry by navController.currentBackStackEntryAsState()
    // 取得當前頁面的名稱
    val currentScreen = Screen.valueOf(
        // destination是目前顯示的頁面，若為null則設定BookList這頁為目前顯示的頁面
        backStackEntry?.destination?.route ?: Screen.BookList.name
    )
    // 從StateFlow取得最新資料
    val books by bookViewModel.booksState.collectAsState()
    val bookEdit by bookViewModel.bookEditState.collectAsState()
    // 設定內容向上捲動時，TopAppBar自動收起來；呼叫pinnedScrollBehavior()則不會收起來
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    // 為了要在Scaffold呈現Snackbar
    val snackbarHostState = remember { SnackbarHostState() }
    // 想呈現Snackbar需要呼叫showSnackbar()，是為suspend方法，需要開啟coroutine
    val scope = rememberCoroutineScope()

    Scaffold(
        // 設定則可追蹤捲動狀態，藉此調整畫面(例如內容向上捲動時，TopAppBar自動收起來)
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MainAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                /* navigateUp()與popBackStack()都可回前頁，但差別是否從其他app來此app首頁：
                   navigateUp()：可以回到來源app，較適合用於左上角的"Up"按鈕
                   popBackStack()：只能單純回到前頁，而無法回到來源app */
                navigateUp = { navController.navigateUp() },
                scrollBehavior = scrollBehavior
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
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
                但若不將navController與ViewModel物件傳給BookListScreen，就需要靠callback函式 */
                BookListScreen(
                    books = books,
                    onAddButtonClick = {
                        navController.navigate(Screen.BookAdd.name)
                    },
                    onEditButtonClick = {
                        bookViewModel.setEditBook(it)
                        navController.navigate(Screen.BookEdit.name)
                    },
                    onDeleteButtonClick = {
                        bookViewModel.removeItem(it)
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                message = context.getString(R.string.deleted_format, it.name),
                                withDismissAction = true
                            )
                        }
                    }
                )
            }
            composable(route = Screen.BookAdd.name) {
                BookAddScreen(
                    onCancel = {
                        navController.popBackStack()
                    },
                    onAdd = {
                        bookViewModel.addItem(it)
                        navController.popBackStack()
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                message = context.getString(R.string.added_format, it.name),
                                withDismissAction = true
                            )
                        }
                    })
            }
            composable(route = Screen.BookEdit.name) {
                BookEditScreen(
                    book = bookEdit,
                    onCancel = {
                        navController.popBackStack()
                    },
                    onEdit = {
                        navController.popBackStack()
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                message = context.getString(R.string.updated_format, it.name),
                                withDismissAction = true
                            )
                        }
                    })
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
    currentScreen: Screen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
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
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    BooksCRUDDemoTheme {
        Main()
    }
}