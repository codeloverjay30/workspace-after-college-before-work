@file:Suppress("OPT_IN_USAGE_FUTURE_ERROR")

package com.tibame.tip101.group02.finalproject.healthhelper.components.picker.datepicker

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
import com.tibame.tip101.group02.finalproject.healthhelper.components.picker.datepicker.MyDatePickerDialog

object MyDatePickerDialogTest {
    @Composable
    fun test1(){
        Column{
            val dateSelectedText = remember { mutableStateOf("") }
            val myDatePickerDialog = MyDatePickerDialog(
                buttonText = "Change Date",
                modifier = Modifier.height(40.dp).width(200.dp),
                onDateSelected = { dateSelectedText.value = it }
            )

            Spacer(modifier = Modifier.height(20.dp).fillMaxWidth())
            myDatePickerDialog.popUp()
            Spacer(modifier = Modifier.height(20.dp).fillMaxWidth())
            Text(dateSelectedText.value)

        }
    }
}