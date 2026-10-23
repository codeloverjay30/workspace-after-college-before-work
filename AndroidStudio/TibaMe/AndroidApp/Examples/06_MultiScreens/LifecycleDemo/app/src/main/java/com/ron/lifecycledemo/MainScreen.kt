package com.ron.lifecycledemo

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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ron.lifecycledemo.ui.theme.LifecycleDemoTheme

/**
 * 建立列舉類型，其屬性代表各頁面
 */
enum class Screen(@StringRes val title: Int) {
    Screen01(title = R.string.screen01),
    Screen02(title = R.string.screen02),
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

/**
 * Main是一個頁面容器，其他頁面會依照使用者操作被加上來
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main(
    navController: NavHostController = rememberNavController()
) {
    // 取得儲存在back stack最上層的頁面
    val backStackEntry by navController.currentBackStackEntryAsState()
    // 取得當前頁面的名稱
    val currentScreen = Screen.valueOf(
        /* route是目前路徑，例如：Screen02 (代表要去Screen02頁面)。
            若要攜帶參數到下頁則為：Screen02/John (John是參數對應的值)，
            此時需要呼叫split().first()取得頁面名稱方便enum Screen比對 */
        backStackEntry?.destination?.route?.split("/")?.first() ?: Screen.Screen01.name
    )
    // 設定內容向上捲動時，TopAppBar自動收起來；呼叫pinnedScrollBehavior()則不會收起來
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    Scaffold(
        // 設定則可追蹤捲動狀態，藉此調整畫面(例如內容向上捲動時，TopAppBar自動收起來)
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MainAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                /* 適合用於"Up"按鈕。navigateUp()與popBackStack()差別如下
                    如果從其他app來此app首頁，呼叫navigateUp()可以回到來源app；popBackStack()則否。
                    popBackStack()只是單純回到前頁 */
                navigateUp = { navController.navigateUp() },
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Screen01.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)

        ) {
            // 設定指定的路徑(route)會到指定的畫面(screen)
            composable(route = Screen.Screen01.name) {
                /* 在composable階層架構中，上層建立的物件傳給下一層，基本上是安全的。
                因為當上層結束，下層也會跟著結束，所以不會產生memory leak問題。
                但要使用remember方式建立傳遞物件；例如使用rememberNavController()建立navController，
                這樣一來無論將navController傳至哪個頁面都能取得navController最新狀態 */
                Screen01(
                    navController = navController
                )
            }
            composable(
                route = "${Screen.Screen02.name}/{uid}"
            ) { backStackEntry ->
                Screen02(
                    navController = navController,
                    uid = backStackEntry.arguments?.getString("uid") ?: ""
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    LifecycleDemoTheme {
        Main()
    }
}
