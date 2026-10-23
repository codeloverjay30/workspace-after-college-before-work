package com.tibame.tip101.group02.finalproject.healthhelper.components.picker.datepicker

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import com.tibame.tip101.group02.finalproject.healthhelper.consts.stringresourcesingleton.StringResourceSingleton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit,
    _selectableDates: SelectableDates? = null,
) {
    val selectableDates = _selectableDates  ?: object : SelectableDates {
        override fun isSelectableDate(utcTimeMillis: Long): Boolean {
            return utcTimeMillis <= System.currentTimeMillis()
        }
    }
    val datePickerState = rememberDatePickerState(
        selectableDates = selectableDates,
    )

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
            }) {
                Text(StringResourceSingleton.OK_OPTION_TEXT)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(StringResourceSingleton.CANCEL_OPTION_TEXT)
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}