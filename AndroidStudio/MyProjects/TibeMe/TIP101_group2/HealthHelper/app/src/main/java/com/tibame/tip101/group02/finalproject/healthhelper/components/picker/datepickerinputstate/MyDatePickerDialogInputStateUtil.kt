package com.tibame.tip101.group02.finalproject.healthhelper.components.picker.datepickerinputstate

import android.annotation.SuppressLint
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import com.tibame.tip101.group02.finalproject.healthhelper.util.dateformatter.DateFormatterPattern
import com.tibame.tip101.group02.finalproject.healthhelper.consts.stringresourcesingleton.StringResourceSingleton
import java.text.SimpleDateFormat
import java.util.Date

/**
 * MyDatePickerDialogUtil
 *
 * a util object for MyDatePickerDialog class
 */


object MyDatePickerDialogInputStateUtil {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun popUp(
        onDateSelected: (String) -> Unit,
        onDismiss: () -> Unit
    ){
        val datePickerState = rememberDatePickerState(
            initialDisplayMode = DisplayMode.Input
        )

        val selectedDate = datePickerState.selectedDateMillis?.let {
            convertMillisToDate(it)
        } ?: ""

        DatePickerDialog(
            onDismissRequest = { onDismiss() },
            confirmButton = {
                Button(onClick = {
                    onDateSelected(selectedDate)
                    onDismiss()
                }

                ) {
                    Text(text = StringResourceSingleton.OK_OPTION_TEXT)
                }
            },
            dismissButton = {
                Button(onClick = {
                    onDismiss()
                }) {
                    Text(text = StringResourceSingleton.CANCEL_OPTION_TEXT)
                }
            }
        ) {
            DatePicker(
                state = datePickerState
            )
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun convertMillisToDate(millis: Long): String {
        val formatter = SimpleDateFormat(DateFormatterPattern.pattern)
        return formatter.format(Date(millis))
    }
}