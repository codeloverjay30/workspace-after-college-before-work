package com.example.dietapp.util.components.button.radiobutton

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

/**
 * MyDatePickerDialog
 *
 * a class than can pop up a list of radio button (with single choice).
 *
 * Notice that
 *
 * + In the list of radio button, only one radio button can be selected at same time.
 *
 * It will deselect other radio buttons when selecting a radio button.
 */

class MyRadioButton(
    private val radioOptions: List<String>,
    private val selectedOption:MutableState<String>,
    private val onOptionSelected:((String)->Unit)
){
    @Composable
    fun popUp(){
        // Note that Modifier.selectableGroup() is essential to ensure correct accessibility behavior
        Column(Modifier.selectableGroup()) {
            radioOptions.forEach { text ->
                Row(
                    Modifier.fillMaxWidth()
                        .height(36.dp)
                        .selectable(
                            selected = (text == selectedOption.value),
                            onClick = { onOptionSelected(text) },
                            role = Role.RadioButton
                        )
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (text == selectedOption.value),
                        onClick = {selectedOption.value = text} // null recommended for accessibility with screenreaders
                    )
                    Text(
                        text = text,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }
    }
}