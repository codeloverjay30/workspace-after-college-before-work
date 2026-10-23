package com.ron.ex04_4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.ron.ex04_4.ui.theme.Ex04_4Theme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Ex04_4Theme {
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
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            // 建立small TopAppBar，預設為置左對齊
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text(text = "friends") },
                scrollBehavior = scrollBehavior
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { innerPadding ->
        // 一定要套用innerPadding，否則內容無法跟TopAppBar對齊
        FriendLists(fetchFriends(), innerPadding) { friend ->
            scope.launch {
                snackbarHostState.showSnackbar(
                    "${friend.name}, ${friend.phone}",
                    withDismissAction = true
                )
            }
        }
    }
}

/**
 * 列表內容
 * @param friends 欲顯示的書籍清單
 * @param innerPadding 元件要套用innerPadding，否則內容無法跟TopAppBar對齊
 * @param onItemClick 點擊列表項目時所需呼叫的函式
 */
@Composable
fun FriendLists(
    friends: List<Friend>,
    innerPadding: PaddingValues,
    onItemClick: (Friend) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        items(friends) { friend ->
            // 用來建立Lists內容物
            ListItem(
                modifier = Modifier.clickable {
                    onItemClick(friend)
                },
                headlineContent = { Text(friend.name) },
                supportingContent = { Text(friend.phone) },
                leadingContent = {
                    Image(
                        painter = painterResource(id = friend.image),
                        contentDescription = "friend"
                    )
                }
            )
            HorizontalDivider()
        }
    }
}

/**
 * 載入測試需要資料
 * @return 多本書資訊
 */
fun fetchFriends(): List<Friend> {
    return listOf(
        Friend("0001", "Ivy", "0912343420", R.drawable.ivy),
        Friend("0002", "Mary", "0908934730", R.drawable.mary),
        Friend("0003", "Sue", "0987466644", R.drawable.sue),
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Ex04_4Theme {
        Main()
    }
}