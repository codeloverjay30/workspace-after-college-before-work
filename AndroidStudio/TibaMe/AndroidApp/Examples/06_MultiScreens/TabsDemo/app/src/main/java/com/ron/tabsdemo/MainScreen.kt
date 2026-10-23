package com.ron.tabsdemo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ron.tabsdemo.ui.theme.TabsDemoTheme

@Composable
fun Main(tabViewModel: TabViewModel = viewModel()) {
    val tabVisibility = tabViewModel.tabVisibility.collectAsState()
    // 儲存當前頁籤索引，一開始設定為0，代表要顯示BookMain頁面
    var tabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf(
        stringResource(id = R.string.home),
        stringResource(id = R.string.about),
        stringResource(id = R.string.settings)
    )

    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                // Take remaining vertical space
                .weight(1f)
        ) {
            when (tabIndex) {
                0 -> BookMain()
                1 -> AboutScreen()
                2 -> SettingsScreen()
            }
        }
        /* 判斷首頁是否顯示tabs:
            如果進入BookDetailScreen就要隱藏，將tabVisibility設為false。
            如果回到BookListScreen就要顯示，將tabVisibility設為true。
            因為需跨頁設定，要將tabVisibility存放在ViewModel */
        if (tabVisibility.value) {
            // 改用ScrollableTabRow，當頁籤數量過多時可以滑動
            TabRow(
                selectedTabIndex = tabIndex,
                containerColor = colorResource(id = R.color.teal_200)
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(text = { Text(title) },
                        // 判斷此頁籤是否為選取頁籤
                        selected = index == tabIndex,
                        // 點擊此頁籤後將選取索引改為此頁籤的索引
                        onClick = { tabIndex = index },
                        // 設定選取顏色
                        selectedContentColor = colorResource(id = R.color.purple_700),
                        // 設定未選取顏色
                        unselectedContentColor = Color.Gray,
                        icon = {
                            when (index) {
                                0 -> Icon(
                                    imageVector = Icons.Default.Home,
                                    contentDescription = "Home"
                                )

                                1 -> Icon(
                                    imageVector = Icons.Default.Info,
                                    contentDescription = "Info"
                                )

                                2 -> Icon(
                                    imageVector = Icons.Default.Settings,
                                    contentDescription = "Settings"
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    TabsDemoTheme {
        Main()
    }
}