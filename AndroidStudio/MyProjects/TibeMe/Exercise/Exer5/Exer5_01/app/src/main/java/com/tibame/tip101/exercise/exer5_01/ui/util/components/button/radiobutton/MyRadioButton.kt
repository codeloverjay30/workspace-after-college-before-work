package com.tibame.tip101.exercise.exer5_01.ui.util.components.button.radiobutton

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.tibame.tip101.exercise.exer5_01.R

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

class MyRadioButton<T>(
    private val title: String,
    private val radioOptions: List<T>,
    private val selectedColor : Color,
    private val unSelectedColor : Color,
    private val selectedOption:MutableState<T>,
    private val onOptionSelected:((T)->Unit)
){
    @Composable
    fun popUp(){
        // Note that Modifier.selectableGroup() is essential to ensure correct accessibility behavior
        Column(
            modifier = Modifier.selectableGroup().width(200.dp) ,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = title,
                modifier = Modifier.padding(start = 26.dp)
            )
            radioOptions.forEach { text ->
                Row(
                    Modifier.fillMaxWidth()
                        .height(20.dp)
                        .selectable(
                            selected = (text == selectedOption.value),
                            onClick = { onOptionSelected(text) },
                            role = Role.RadioButton
                        )
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (text== selectedOption.value),
                        onClick = {selectedOption.value = text} ,// null recommended for accessibility with screenreaders
                        colors = RadioButtonDefaults.colors(
                            selectedColor = selectedColor,
                            unselectedColor = unSelectedColor,
                        )
                    )
                    Text(
                        text = text.toString(),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
}