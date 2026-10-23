package com.ron.bottomsheetdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ron.bottomsheetdemo.ui.theme.BottomSheetDemoTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomSheetDemoTheme {
                Main()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main() {
    var message by remember { mutableStateOf("") }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                // 設定顏色
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.teal_200),
                    titleContentColor = colorResource(id = R.color.purple_700)
                ),
                title = { Text(text = stringResource(id = R.string.shopping_cart)) })
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(text = stringResource(id = R.string.pay_credit_card)) },
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.credit_card),
                        contentDescription = "credit card"
                    )
                },
                // 點擊後顯示bottom sheet
                onClick = {
                    showBottomSheet = true
                }
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Android App\t $700\n" +
                        "MySQL DB\t $600\n" +
                        "-----------------------------\n" +
                        "Total: $1,300",
                textAlign = TextAlign.End
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = message, color = Color.Magenta)
        }

        if (showBottomSheet) {
            ModalBottomSheet(
                // 使用者點擊bottom sheet以外區域時執行
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState,
                containerColor = colorResource(id = R.color.teal_200)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        /* 高度0.2代表佔20%。經由測試，值超過0.5仍舊與0.5高度一樣，
                            但可以向上滑動到指定高度 */
                        .fillMaxHeight(0.2f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Total: $1,300")
                    Spacer(modifier = Modifier.height(16.dp))
                    Row {
                        Button(onClick = {
                            // 不是composable元件，無法使用stringResource()，改用getString()取得文字
                            message = context.getString(R.string.payment_successful)
                            /* 呼叫hide()才會以動畫且柔和方式關閉；
                                僅將showBottomSheet設為false會直接關閉且很突兀 */
                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    showBottomSheet = false
                                }
                            }
                        }) {
                            Text(text = stringResource(id = R.string.pay))
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(onClick = {
                            message = ""
                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    showBottomSheet = false
                                }
                            }
                        }) {
                            Text(text = stringResource(id = R.string.cancel))
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BottomSheetDemoTheme {
        Main()
    }
}