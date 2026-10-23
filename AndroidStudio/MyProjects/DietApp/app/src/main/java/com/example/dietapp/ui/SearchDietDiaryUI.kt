package com.example.dietapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.unit.dp
import com.example.dietapp.resources.stringresources.StringResourcesSingleton
import com.example.dietapp.util.components.button.checkbox.MyCheckBox
import com.example.dietapp.util.components.picker.datepicker.MyDatePickerDialog
import com.example.dietapp.util.components.button.radiobutton.MyRadioButton

object SearchDietDiaryUI {
    @Composable
    fun show() {
        val keyboardOptions = KeyboardOptions.Default.copy(
            hintLocales = LocaleList(Locale("us"))
        )
        val dateButtonText = StringResourcesSingleton.OPEN_DATE_PICKER_DIALOG
        val andOr = listOf(StringResourcesSingleton.AND,StringResourcesSingleton.OR)

        var name by remember { mutableStateOf("") }
        val checked1 = remember { mutableStateOf(true) }
        val checked2 = remember { mutableStateOf(true) }
        val selectedOption = remember { mutableStateOf(andOr[0]) }
        val dateSelectedText = remember { mutableStateOf("") }

        var searchButtonIsClicked by remember { mutableStateOf(false) }

        val myCheckBox1 = MyCheckBox(
            StringResourcesSingleton.NOT,
            checked = checked1,
            onCheckedChange = {checked1.value = it}
        )

        val myRadioButton = MyRadioButton(
            andOr,
            selectedOption = selectedOption,
            onOptionSelected = {selectedOption.value = it}
        )

        val myCheckBox2 = MyCheckBox(
            StringResourcesSingleton.NOT,
            checked = checked2,
            onCheckedChange = {checked2.value = it}
        )

        val myDatePickerDialog = MyDatePickerDialog(
            buttonText = dateButtonText,
            modifier = Modifier.height(40.dp).width(200.dp),
            onDateSelected = { dateSelectedText.value = it }
        )

        if(searchButtonIsClicked){
            DietDiaryQueryResultUI.show()
        }else {
            Column {
                Spacer(modifier = Modifier.height(20.dp).fillMaxWidth())
                Row {
                    myCheckBox1.popUp()
                    Text("${StringResourcesSingleton.NAME}:")
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text(StringResourcesSingleton.NAME) },
                        placeholder = { Text("") },
                        keyboardOptions = keyboardOptions
                    )
                }

                Spacer(modifier = Modifier.height(20.dp).fillMaxWidth())

                myRadioButton.popUp()

                Spacer(modifier = Modifier.height(20.dp).fillMaxWidth())

                myCheckBox2.popUp()

                Spacer(modifier = Modifier.height(20.dp).fillMaxWidth())
                Text("${StringResourcesSingleton.DATE}:")

                myDatePickerDialog.popUp()

                Button(
                    onClick = {searchButtonIsClicked = true}
                ) {
                    Text(StringResourcesSingleton.SEARCH)
                }
            }
        }
    }
}