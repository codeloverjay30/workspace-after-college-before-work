package com.example.dietapp.util.components.picker.datepickerinputstate

import android.annotation.SuppressLint
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.dietapp.R
import com.example.dietapp.resources.stringresources.StringResourcesSingleton
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
                    Text(text = StringResourcesSingleton.OK)
                }
            },
            dismissButton = {
                Button(onClick = {
                    onDismiss()
                }) {
                    Text(text = StringResourcesSingleton.CANCEL)
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
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        return formatter.format(Date(millis))
    }
}