package com.ron.pulltorefreshdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ron.pulltorefreshdemo.ui.theme.PullToRefreshDemoTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PullToRefreshDemoTheme {
                Main()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main() {
    var itemCount by remember { mutableIntStateOf(15) }
    var isRefreshing by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val state = rememberPullToRefreshState()
    val onRefresh: () -> Unit = {
        isRefreshing = true
        // 開啟一個coroutine來做事
        coroutineScope.launch {
            // 模擬抓資料需要等待1000毫秒(1秒)
            delay(1000)
            // 模擬一次抓到5筆資料
            itemCount += 5
            isRefreshing = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("TopAppBar") },
                // 另外在TopAppBar提供下拉更新按鈕(非必要)
                actions = {
                    IconButton(onClick = { onRefresh() }) {
                        Icon(Icons.Filled.Refresh, "refresh")
                    }
                }
            )
        }
    ) {
        PullToRefreshBox(
            modifier = Modifier.padding(it),
            state = state,
            isRefreshing = isRefreshing,
            onRefresh = onRefresh,
        ) {
            LazyColumn(Modifier.fillMaxSize()) {
                // itemCount為15，則index為「0, 1, 2, ...14」
                // 「itemCount - index」讓數字由大至小列印，模擬最新資料在最上方
                items(itemCount) { index ->
                    ListItem({ Text(text = "Item ${itemCount - index}") })
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PullToRefreshDemoTheme {
        Main()
    }
}