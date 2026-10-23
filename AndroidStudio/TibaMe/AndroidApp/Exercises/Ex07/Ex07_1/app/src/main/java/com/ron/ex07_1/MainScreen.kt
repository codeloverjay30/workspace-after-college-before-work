package com.ron.ex07_1

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ron.ex07_1.ui.theme.Ex07_1Theme

/**
 * 建立列舉類型，其屬性代表各頁面
 */
enum class Screen(@StringRes val title: Int) {
    Home(title = R.string.home),
    Preferences(title = R.string.preferences)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main(navController: NavHostController = rememberNavController()) {
    // 取得儲存在back stack最上層的頁面
    val backStackEntry by navController.currentBackStackEntryAsState()
    // 取得當前頁面的名稱
    val currentScreen = Screen.valueOf(
        // destination是目前顯示的頁面，若為null則設定Home這頁為目前顯示的頁面
        backStackEntry?.destination?.route ?: Screen.Home.name
    )
    // 呼叫pinnedScrollBehavior()，內容向上捲動時，TopAppBar不會收起來
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
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
            startDestination = Screen.Home.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)

        ) {
            composable(route = Screen.Home.name) {
                HomeScreen()
            }
            composable(
                route = Screen.Preferences.name
            ) {
                PreferencesScreen()
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
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        },
        actions = {
            // 不是偏好設定頁面需顯示偏好設定按鈕
            if (currentScreen != Screen.Preferences) {
                IconButton(onClick = {
                    navController.navigate(Screen.Preferences.name)
                }) {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = stringResource(id = R.string.settings)
                    )
                }
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    Ex07_1Theme {
        Main()
    }
}