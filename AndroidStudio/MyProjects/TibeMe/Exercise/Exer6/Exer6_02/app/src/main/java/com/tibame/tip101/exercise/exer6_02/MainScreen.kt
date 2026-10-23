package com.tibame.tip101.exercise.exer6_02

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tibame.tip101.exercise.exer6_02.ui.theme.Exer6_02Theme
import kotlinx.coroutines.launch

/**
 * 建立列舉類型，其屬性代表各頁面
 */
enum class Screen(@StringRes val title: Int) {
    BookList(title = R.string.book_list),
    BookAdd(title = R.string.book_add),
    BookEdit(title = R.string.book_edit),
    HelpPage(title = R.string.help_page),
    InfoPage(title = R.string.info_page),
}

private val TAG = "Main"

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
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = Screen.valueOf(
        backStackEntry?.destination?.route ?: Screen.BookList.name
    )
    val books by bookViewModel.booksState.collectAsState()
    val bookEdit by bookViewModel.bookEditState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val bookDetail by bookViewModel.bookDetailState.collectAsState()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MainAppBar(
                navController = navController,
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
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
                BookListScreen(
                    books = books,
                    onItemClick = {
                        bookViewModel.setDetailBook(it)
                        navController.navigate(Screen.InfoPage.name)
                    },
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

            composable(route = Screen.HelpPage.name) {
                val help_message = stringResource(R.string.help_message)
                HelpScreen(help_message)
            }

            composable(route = Screen.InfoPage.name) {
                InfoPage(
                    navController = navController,
                    book = bookDetail,
                )
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
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        },
        scrollBehavior = scrollBehavior,
        actions = {
            IconButton(
                onClick = { navController.navigate(Screen.HelpPage.name)}
            ){
                Icon(
                    painter = painterResource(R.drawable.help_icon),
                    contentDescription = stringResource(R.string.help_icon)
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    Exer6_02Theme {
        Main()
    }
}