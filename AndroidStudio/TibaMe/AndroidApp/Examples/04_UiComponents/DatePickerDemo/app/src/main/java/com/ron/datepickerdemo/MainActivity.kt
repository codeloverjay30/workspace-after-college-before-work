package com.ron.datepickerdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ron.datepickerdemo.ui.theme.DatePickerDemoTheme
import java.time.DayOfWeek
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter.ofLocalizedDate
import java.time.format.FormatStyle

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DatePickerDemoTheme {
                Main()
            }
        }
    }
}

// 使用的DatePicker屬於androidx.compose.material3測試功能，需要加上"@OptIn"註記
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main() {
    val datePickerState = rememberDatePickerState(
        // SelectableDates介面用來限制可選擇的日期與年，
        selectableDates = object : SelectableDates {
            // 週六日不可選擇
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

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        DatePicker(
            state = datePickerState,
            title = { Text(text = "XYZ Hotel") },
            headline = { Text(text = "Check-in Date") }
        )
        // 顯示選取日期(已格式化)
        Text(
            text = "Selected date: ${
                datePickerState.selectedDateMillis?.let {
                    Instant.ofEpochMilli(it).atZone(ZoneId.of("UTC"))
                        .toLocalDate().format(ofLocalizedDate(FormatStyle.MEDIUM))
                } ?: "no selection"
            }"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DatePickerDemoTheme {
        Main()
    }
}