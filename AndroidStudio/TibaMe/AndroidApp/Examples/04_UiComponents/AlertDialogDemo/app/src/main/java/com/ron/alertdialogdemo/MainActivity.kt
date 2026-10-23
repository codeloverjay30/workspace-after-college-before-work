package com.ron.alertdialogdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ron.alertdialogdemo.ui.theme.AlertDialogDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlertDialogDemoTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    // 是否顯示alert dialog；false代表不顯示
    var showAlert by remember { mutableStateOf(false) }
    // 訊息文字
    var message by remember { mutableStateOf("Data ...") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(text = message)
        // 點擊按鈕會跳出alert dialog
        Button(onClick = { showAlert = true }) {
            Text("Delete Data")
        }
        if (showAlert) {
            DeleteDataDialog(
                // 點擊對話視窗外部或 back 按鈕時呼叫
                onDismissRequest = {
                    message = "Clicking outside or pressing the back button."
                    showAlert = false
                },
                // 設定確定時欲執行內容
                onConfirm = {
                    message = "Data deleted!"
                    showAlert = false
                },
                // 設定取消時欲執行內容
                onDismiss = {
                    message = "Cancelled"
                    showAlert = false
                }
            )
        }
    }
}

@Composable
fun DeleteDataDialog(
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    // alert dialog屬於簡易、制式化的對話視窗
    AlertDialog(
        // 點擊對話視窗外部或back按鈕時呼叫，並非點擊dismissButton時呼叫
        onDismissRequest = onDismissRequest,
        title = { Text("Delete Data") },
        text = { Text("Are you sure you want to delete this data?") },
        // 設定確定按鈕
        confirmButton = {
            Button(
                onClick = { onConfirm() }
            ) {
                Text("Delete")
            }
        },
        // 設定取消按鈕
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AlertDialogDemoTheme {
        Main()
    }
}