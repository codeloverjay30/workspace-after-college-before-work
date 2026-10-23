package com.example.dietapp.util.components.picker.datepickerinputstate

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

object MyDatePickerDialogInputStateTest {
    @Composable
    fun test1(){
        Column{
            val dateSelectedText = remember { mutableStateOf("") }
            val myDatePickerDialog = MyDatePickerDialogInputState(
                buttonText = "Change Date",
                modifier = Modifier.height(40.dp).width(200.dp),
                onDateSelected = { dateSelectedText.value = it }
            )
            Spacer(modifier = Modifier.height(20.dp).fillMaxWidth())
            myDatePickerDialog.popUp()
            Spacer(modifier = Modifier.height(20.dp).fillMaxWidth())
            Text(dateSelectedText.value.toString())
        }
    }
}