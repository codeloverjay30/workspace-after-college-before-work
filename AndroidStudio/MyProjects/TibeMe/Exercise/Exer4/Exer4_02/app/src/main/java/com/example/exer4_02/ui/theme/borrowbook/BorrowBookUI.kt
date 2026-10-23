package com.example.exer4_02.ui.theme.borrowbook

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.dietapp.util.components.picker.datepicker.MyDatePickerDialog
import com.example.exer4_02.ui.components.slider.slider.MySlider
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter.ofPattern
import java.time.temporal.ChronoUnit
import kotlin.math.abs

object BorrowBookUI {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun popUp() {
        val dateFormatter = ofPattern("yyyy-MM-dd")
        val buttonText = "Change Button"

        val minValue = 1f
        val maxValue = 30f

        var selectedDate by remember { mutableStateOf(LocalDate.now().format(dateFormatter)) }
        val dateSelectedText =  remember { mutableStateOf("") }
        val sliderPosition = remember {  mutableIntStateOf(1) }
        var message = remember { mutableStateOf("") }

        val selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                val today = LocalDate.now()
                val lastMonth = today.minusMonths(1)
                val selectedDate = Instant.ofEpochMilli(utcTimeMillis).atZone(ZoneId.of("UTC"))
                    .toLocalDate()
                return ChronoUnit.DAYS.between(lastMonth, selectedDate).toInt() >= 0 &&
                        ChronoUnit.DAYS.between(selectedDate, today).toInt() >= 0
            }
        }

        val myDatePickerDialog =  MyDatePickerDialog(
            buttonText = buttonText,
            modifier = Modifier.height(40.dp).width(300.dp),
            onDateSelected = { dateSelectedText.value = it },
            selectableDates = selectableDates
        )

        val mySlider = MySlider(
            sliderPosition = sliderPosition,
            thumbColor = Color.Blue,
            activeTrackColor = Color.Blue,
            inactiveTrackColor = Color.White,
            steps = (maxValue - minValue).toUInt(),
            valueRange = minValue..maxValue
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Date borrowed: ${dateSelectedText.value.toString()}")
            myDatePickerDialog.popUp()

            Text("Days can be borrowed: ${sliderPosition.value.toString()}")
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = minValue.toInt().toString(),
                    modifier = Modifier.padding(end = 8.dp)
                )
                mySlider.popUp()
                Text(
                    text = maxValue.toInt().toString(),
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
            Spacer(modifier = Modifier.height(40.dp).fillMaxWidth())
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    onClick = {
                        val borrowDate = LocalDate.parse(selectedDate, dateFormatter)
                        val days = sliderPosition.value
                        val returnDate = borrowDate.plusDays(days.toLong())
                        message.value =
                            "The book should be returned before: ${returnDate.format(dateFormatter)}.\n"
                        val today = LocalDate.now()
                        message.value += "Today is ${today.format(dateFormatter)}.\n"
                        val daysBetween = ChronoUnit.DAYS.between(today, returnDate).toInt()
                        message.value += if (daysBetween > 0) {
                            "You should return the book in $daysBetween day(s).\n"
                        } else if (daysBetween == 0) {
                            "You should return the book today.\n"
                        } else {
                            val fineDays = abs(daysBetween)
                            val fines = fineDays * 1
                            "This book requires a fine of $fines dollar(s) for a $fineDays-day overdue period."
                        }
                    }
                ) {
                    Text("Submit")
                }
                Text(message.value.toString())
            }
        }
    }
}