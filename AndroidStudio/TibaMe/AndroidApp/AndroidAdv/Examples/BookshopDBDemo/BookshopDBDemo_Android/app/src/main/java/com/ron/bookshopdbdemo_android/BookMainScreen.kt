package com.ron.bookshopdbdemo_android

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ron.bookshopdbdemo_android.ui.theme.BookshopDemo_AndroidTheme

/** 建立列舉類型，其屬性代表各頁面 */
enum class Screen(@StringRes val title: Int) {
    Books(title = R.string.books),
    BookDetail(title = R.string.book_detail)
}

/** BookMainScreen是書籍的NavHost */
@Composable
fun BookMainScreen(
    bookVM: BookVM = viewModel(),
    navController: NavHostController = rememberNavController(),
    onLogout: () -> Unit
) {
    LocalContext.current
    // 取得儲存在back stack最上層的頁面
    val backStackEntry by navController.currentBackStackEntryAsState()
    // 取得當前頁面的名稱
    val currentScreen = Screen.valueOf(
        // destination是目前顯示的頁面，若為null則設定BookList這頁為目前顯示的頁面
        backStackEntry?.destination?.route ?: Screen.Books.name
    )
    val books by bookVM.booksState.collectAsState()
    val book by bookVM.bookSelectedState.collectAsState()

    Scaffold(
        topBar = {
            MainAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                /* navigateUp()與popBackStack()都可回前頁，但差別是否從其他app來此app首頁：
                   navigateUp()：可以回到來源app，較適合用於左上角的"Up"按鈕
                   popBackStack()：只能單純回到前頁，而無法回到來源app */
                navigateUp = { navController.navigateUp() },
                onLogout = onLogout
            )
        }

    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Books.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(route = Screen.Books.name) {
                /* 在composable階層架構中，上層建立的物件傳給下一層，基本上是安全的。
                所以可將navController或ViewModel物件傳給下一個Composable。
                但若不將navController與ViewModel物件傳給BookListScreen，就需要靠callback函式 */
                BooksScreen(
                    books,
                    onItemClick = {
                        // 將點擊的book存入view model
                        bookVM.setSelectedBook(it)
                        navController.navigate(Screen.BookDetail.name)
                    }
                )
            }
            composable(route = Screen.BookDetail.name) {
                BookDetailScreen(book)
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
    onLogout: () -> Unit
) {
    TopAppBar(
        // 設定頁面標題
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        navigationIcon = {
            // 如果可回上頁，就顯示Back按鈕
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "back"
                    )
                }
            }
        },
        actions = {
            Button(onClick = onLogout) {
                Text(text = stringResource(id = R.string.logout))
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun BookMainScreenPreview() {
    BookshopDemo_AndroidTheme {
        BookMainScreen(onLogout = {})
    }
}