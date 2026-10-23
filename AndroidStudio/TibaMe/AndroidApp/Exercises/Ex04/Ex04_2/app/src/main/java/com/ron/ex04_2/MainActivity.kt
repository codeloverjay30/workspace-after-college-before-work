package com.ron.ex04_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ron.ex04_2.ui.theme.Ex04_2Theme
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter.ofPattern
import java.time.temporal.ChronoUnit
import kotlin.math.abs


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Ex04_2Theme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    val dateFormatter = ofPattern("yyyy-MM-dd")
    // 是否顯示date picker dialog；false代表不顯示
    var showDatePickerDialog by remember { mutableStateOf(false) }
    // 選取日期，一開始顯示今天日期
    var selectedDate by remember { mutableStateOf(LocalDate.now().format(dateFormatter)) }
    val minValue = 1f
    val maxValue = 30f
    var sliderValue by remember { mutableIntStateOf(minValue.toInt()) }
    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(text = "Date borrowed: $selectedDate")
        // 點擊按鈕會跳出date picker dialog
        Button(onClick = { showDatePickerDialog = true }) {
            Text("Change Date")
        }
        if (showDatePickerDialog) {
            MyDatePickerDialog(
                // 確定時會接收到選取日期
                onConfirm = { utcTimeMillis ->
                    selectedDate = utcTimeMillis?.let {
                        Instant.ofEpochMilli(it).atZone(ZoneId.of("UTC"))
                            .toLocalDate().format(dateFormatter)
                    } ?: "no selection"
                    showDatePickerDialog = false
                },
                // 設定取消時欲執行內容
                onDismiss = {
                    showDatePickerDialog = false
                }
            )
        }
        Text(text = "Days can be borrowed: $sliderValue")
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            // 顯示最小值(整數)
            Text(text = "${minValue.toInt()}", modifier = Modifier.padding(end = 8.dp))
            Slider(
                value = sliderValue.toFloat(), // 目前的值
                valueRange = minValue..maxValue, // 值的範圍
                onValueChange = { sliderValue = it.toInt() }, // 值改變時的callback
                /* weight(1.0f): Row父元件寬度減掉其他text子元件必要寬度後，
                    剩下來的所有寬度空間給slider。
                   weight(0.9f): slider佔90%空間，剩下10%分給其他text */
                modifier = Modifier.weight(1.0f),
            )
            // 顯示最大值(整數)
            Text(text = "${maxValue.toInt()}", modifier = Modifier.padding(8.dp))
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(onClick = {
                val borrowDate = LocalDate.parse(selectedDate, dateFormatter)
                val days = sliderValue
                val returnDate = borrowDate.plusDays(days.toLong())
                message =
                    "The book should be returned before: ${returnDate.format(dateFormatter)}.\n"
                val today = LocalDate.now()
                message += "Today is ${today.format(dateFormatter)}.\n"
                // 計算今日與應還書日的差值
                val daysBetween = ChronoUnit.DAYS.between(today, returnDate).toInt()
                // "daysBetween > 0"代表今日在應還書日之前
                message += if (daysBetween > 0) {
                    "You should return the book in $daysBetween day(s).\n"
                } else if (daysBetween == 0) {
                    "You should return the book today.\n"
                } else {
                    val fineDays = abs(daysBetween)
                    val fines = fineDays * 1
                    "This book requires a fine of $fines dollar(s) for a $fineDays-day overdue period."
                }
            }) {
                Text(text = "Submit")
            }
            Text(
                text = message,
                modifier = Modifier.padding(16.dp)
            )
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDatePickerDialog(
    onConfirm: (message: Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState(
        // SelectableDates介面用來限制可選擇的日期與年，
        selectableDates = object : SelectableDates {
            // 日期可挑選範圍：前一個月到今日
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                // 今日
                val today = LocalDate.now()
                // 前一個月
                val lastMonth = today.minusMonths(1)
                // 選取日期
                val selectedDate = Instant.ofEpochMilli(utcTimeMillis).atZone(ZoneId.of("UTC"))
                    .toLocalDate()
                return ChronoUnit.DAYS.between(lastMonth, selectedDate).toInt() >= 0 &&
                        ChronoUnit.DAYS.between(selectedDate, today).toInt() >= 0
            }
        }
    )

    DatePickerDialog(
        // 點擊對話視窗外部或back按鈕時呼叫，並非點擊dismissButton時呼叫
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
                // 點擊確定按鈕時會呼叫onConfirm()並將選取日期傳入以回饋給原畫面
                onClick = {
                    onConfirm(datePickerState.selectedDateMillis)
                }
            ) {
                Text("Confirm")
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
    Ex04_2Theme {
        Main()
    }
}