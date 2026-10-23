package com.ron.scaffolddemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ron.scaffolddemo.ui.theme.ScaffoldDemoTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScaffoldDemoTheme {
                Main()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main() {
    // 設定內容向上捲動時，TopAppBar自動收起來
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    // 改呼叫TopAppBarDefaults.pinnedScrollBehavior()則TopAppBar不會自動收起來
    // val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        // 設定則可追蹤捲動狀態，藉此調整畫面(例如內容向上捲動時，TopAppBar自動收起來)
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            // Top app bar置中對齊
            CenterAlignedTopAppBar(
                // 設定顏色
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                // 設定標題文字
                title = {
                    Text(text = "Top App Bar")
                },
                // 設定最前方圖示
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                "TopAppBar - Back",
                                withDismissAction = true
                            )
                        }
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "back"
                        )
                    }
                },
                // 動作按鈕
                actions = {
                    IconButton(onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                "TopAppBar - ShoppingCart",
                                withDismissAction = true
                            )
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ShoppingCart,
                            contentDescription = "shoppingCart"
                        )
                    }
                },
                // 設定內容捲動時，TopAppBar的行為模式
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = {
            BottomAppBar(
                // 動作按鈕
                actions = {
                    IconButton(onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                "BottomAppBar - Check",
                                withDismissAction = true
                            )
                        }
                    }) {
                        Icon(Icons.Filled.Check, contentDescription = "check")
                    }
                    IconButton(onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                "BottomAppBar - Edit",
                                withDismissAction = true
                            )
                        }
                    }) {
                        Icon(
                            Icons.Filled.Edit,
                            contentDescription = "edit",
                        )
                    }
                    IconButton(onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                "BottomAppBar - Delete",
                                withDismissAction = true
                            )
                        }
                    }) {
                        Icon(
                            Icons.Filled.Delete,
                            contentDescription = "delete",
                        )
                    }
                },
                // BottomAppBar也可放FloatingActionButton
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    "BottomAppBar - Add",
                                    withDismissAction = true
                                )
                            }
                        },
                        containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                    ) {
                        Icon(Icons.Filled.Add, "Localized description")
                    }
                }
            )
        },
        // 設定Snackbar呈現位置
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            "FloatingActionButton - Add",
                            withDismissAction = true
                        )
                    }
                },
            ) {
                Icon(Icons.Filled.Add, "add")
            }
        },
        // 預設即為End，所以可以省略不寫
        // floatingActionButtonPosition = FabPosition.End
    ) { innerPadding ->
        // 一定要套用innerPadding，否則內容無法跟TopAppBar對齊
        ScrollContent(innerPadding)
    }
}

/**
 * 可捲動內容
 * @param innerPadding 元件要套用innerPadding，否則內容無法跟TopAppBar對齊
 */
@Composable
fun ScrollContent(innerPadding: PaddingValues) {
    LazyColumn(
        modifier = Modifier
            .padding(innerPadding)
    ) {
        items(25) { number ->
            Text(
                text = "Item ${number + 1}", modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ScaffoldDemoTheme {
        Main()
    }
}