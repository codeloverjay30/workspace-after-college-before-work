package com.tibame.exercise.exer7.exer7_01

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
import com.tibame.exercise.exer7.exer7_01.ui.theme.Ex7_01Theme

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
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = Screen.valueOf(
        backStackEntry?.destination?.route ?: Screen.Home.name
    )
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
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
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
    Ex7_01Theme {
        Main()
    }
}