package com.example.dietapp.util.components.button.checkbox

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment

class MyCheckBox(
    private val checkBoxText: String,
    private val checked:MutableState<Boolean>,
    private val onCheckedChange: ((Boolean) -> Unit)?
){
    @Composable
    fun popUp(){
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(checkBoxText)
            Checkbox(
                checked = checked.value,
                onCheckedChange = onCheckedChange
            )
        }
    }
}