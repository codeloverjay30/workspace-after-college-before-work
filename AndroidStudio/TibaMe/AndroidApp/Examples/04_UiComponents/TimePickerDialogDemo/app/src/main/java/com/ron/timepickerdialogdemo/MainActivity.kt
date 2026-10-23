package com.ron.timepickerdialogdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.ron.timepickerdialogdemo.ui.theme.TimePickerDialogDemoTheme
import java.time.LocalTime
import java.time.format.DateTimeFormatter.ofLocalizedTime
import java.time.format.FormatStyle

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TimePickerDialogDemoTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    // 是否顯示time picker dialog；false代表不顯示
    var showTimePickerDialog by remember { mutableStateOf(false) }
    // 訊息文字
    var message by remember { mutableStateOf("Please select the time.") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(text = message)
        // 點擊按鈕會跳出time picker dialog
        Button(onClick = { showTimePickerDialog = true }) {
            Text("Time Picker")
        }
        if (showTimePickerDialog) {
            MyTimePickerDialog(
                // 確定時會接收到選取時間
                onConfirm = {
                    message = "Selected time: ${it.format(ofLocalizedTime(FormatStyle.SHORT))}"
                    showTimePickerDialog = false
                },
                // 設定取消時欲執行內容
                onDismiss = {
                    showTimePickerDialog = false
                }
            )
        }
    }
}

// 使用的TimePicker屬於androidx.compose.material3測試功能，要加上"@OptIn"註記
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTimePickerDialog(
    onConfirm: (LocalTime) -> Unit,
    onDismiss: () -> Unit
) {
    val timePickerState = rememberTimePickerState()
    // 函式庫沒有TimePickerDialog，可使用自訂Dialog達到同樣目的
    Dialog(onDismissRequest = { onDismiss() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            // 形狀設定為圓角矩形
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                TimePicker(state = timePickerState)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    // 設定取消按鈕
                    TextButton(
                        onClick = { onDismiss() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Cancel")
                    }
                    // 設定確定按鈕
                    TextButton(
                        // 點擊確定按鈕時呼叫onConfirm(LocalTime)並將選取時間傳入以回饋給原畫面
                        onClick = {
                            val selectedTime =
                                LocalTime.of(timePickerState.hour, timePickerState.minute)
                            // 呼叫onConfirm
                            onConfirm(selectedTime)
                        },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("OK")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TimePickerDialogDemoTheme {
        Main()
    }
}

