package com.ron.datepickerdialogdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ron.datepickerdialogdemo.ui.theme.DatePickerDialogDemoTheme
import java.time.DayOfWeek
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter.ofLocalizedDate
import java.time.format.FormatStyle

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DatePickerDialogDemoTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    // 是否顯示date picker dialog；false代表不顯示
    var showDatePickerDialog by remember { mutableStateOf(false) }
    // 訊息文字
    var message by remember { mutableStateOf("Please select the check-in date.") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(text = message)
        // 點擊按鈕會跳出date picker dialog
        Button(onClick = { showDatePickerDialog = true }) {
            Text("Date Picker")
        }
        if (showDatePickerDialog) {
            MyDatePickerDialog(
                onDismissRequest = {
                    message = "Clicking outside or pressing the back button."
                    showDatePickerDialog = false
                },
                // 確定時會接收到選取日期
                onConfirm = { utcTimeMillis ->
                    message = "Selected date: ${
                        utcTimeMillis?.let {
                            Instant.ofEpochMilli(it).atZone(ZoneId.of("UTC"))
                                .toLocalDate().format(ofLocalizedDate(FormatStyle.MEDIUM))
                        } ?: "no selection"
                    }"
                    showDatePickerDialog = false
                },
                // 設定取消時欲執行內容
                onDismiss = {
                    message = "Cancelled"
                    showDatePickerDialog = false
                }
            )
        }
    }
}

// 使用的DatePicker屬於androidx.compose.material3測試功能，需要加上"@OptIn"註記
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDatePickerDialog(
    onDismissRequest: () -> Unit,
    onConfirm: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState(
        // SelectableDates介面用來限制可選擇的日期與年，
        selectableDates = object : SelectableDates {
            // 設定使用者不可選擇週六、日
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                /* 將使用者選取的時間轉成LocalDate物件後取出星期幾的資訊
                   API 26開始支援Instant */
                val dayOfWeek = Instant.ofEpochMilli(utcTimeMillis).atZone(ZoneId.of("UTC"))
                    .toLocalDate().dayOfWeek
                return dayOfWeek != DayOfWeek.SUNDAY && dayOfWeek != DayOfWeek.SATURDAY
            }

            // 只可選擇2024年以後日期
            override fun isSelectableYear(year: Int): Boolean {
                return year >= 2024
            }
        }
    )

    DatePickerDialog(
        // 點擊對話視窗外部或back按鈕時呼叫，並非點擊dismissButton時呼叫
        onDismissRequest = onDismissRequest,
        confirmButton = {
            Button(
                // 點擊確定按鈕時呼叫onConfirm(Long?)並將選取日期傳入以回饋給原畫面
                onClick = {
                    onConfirm(datePickerState.selectedDateMillis)
                }
            ) {
                Text("OK")
            }
        },
        // 設定取消按鈕
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DatePickerDialogDemoTheme {
        Main()
    }
}