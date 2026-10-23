package com.ron.snackbardemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ron.snackbardemo.ui.theme.SnackbarDemoTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SnackbarDemoTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    var uid by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    // 回傳CoroutineScope物件以適用於此compose環境
    val scope = rememberCoroutineScope()

    Column(
        // 內容物水平置中
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp) // 所有padding皆為16
    ) {
        Text(
            text = "Form", fontSize = 30.sp, // 字型大小: 30
            fontWeight = FontWeight.Bold, // 字體樣式: 粗體
            color = Color.Blue // 字的顏色: 藍色
        )

        TextField(
            value = uid,
            // 輸入的值改變時呼叫此方法，並將值傳給自動宣告的變數"it"
            onValueChange = { uid = it },
            // 提示文字
            label = { Text(text = "User ID") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Password") },
            // 密碼樣式
            visualTransformation = PasswordVisualTransformation(),
            // 鍵盤種類為密碼
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier
                .fillMaxWidth()
        )

        Row {
            Button(
                modifier = Modifier.padding(8.dp),
                onClick = {
                    // 呼叫此暫停函式需要使用CoroutineScope(可使用rememberCoroutineScope建立)
                    scope.launch {
                        // 呼叫showSnackbar()會改變SnackbarHostState狀態並顯示Snackbar
                        snackbarHostState.showSnackbar(
                            "$uid\n$password",
                            // 建議加上取消按鈕
                            withDismissAction = true,
                            // 不設定duration，預設為Short(停留短暫並自動消失)
                            // duration = SnackbarDuration.Long
                        )
                    }
                }
            ) {
                Text(text = "Submit")
            }
            Button(
                modifier = Modifier.padding(8.dp),
                onClick = {
                    val tempUid = uid
                    val tempPassword = password
                    uid = ""
                    password = ""
                    // 呼叫此暫停函式需要使用CoroutineScope(可使用rememberCoroutineScope建立)
                    scope.launch {
                        // 可以取得結果
                        val result = snackbarHostState.showSnackbar(
                            "Already clear your input!",
                            withDismissAction = true,
                            // 顯示像按鈕般的標籤
                            actionLabel = "Undo",
                            // 除非明確關閉，否則一直顯示
                            duration = SnackbarDuration.Indefinite
                        )
                        when (result) {
                            // 如果點擊actionLabel
                            SnackbarResult.ActionPerformed -> {
                                uid = tempUid
                                password = tempPassword
                            }
                            // 如果點擊dismiss按鈕
                            SnackbarResult.Dismissed -> {

                            }
                        }
                    }
                }
            ) {
                Text(text = "Clear")
            }
        }

        // 建立SnackbarHost以設定Snackbar顯示位置；至於顯示與否依據SnackbarHostState狀態
        SnackbarHost(hostState = snackbarHostState)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SnackbarDemoTheme {
        Main()
    }
}