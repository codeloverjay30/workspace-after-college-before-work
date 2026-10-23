package com.example.dietapp.util.components.button.radiobutton

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dietapp.resources.stringresources.StringResourcesSingleton

object MyRadioButtonTest {
    @Composable
    fun test1(){
        Column {
            val andOr = listOf(StringResourcesSingleton.AND, StringResourcesSingleton.OR)
            val selectedOption = remember { mutableStateOf(andOr[0]) }
            val myRadioButton = MyRadioButton(
                andOr,
                selectedOption = selectedOption,
                onOptionSelected = {selectedOption.value = it}
            )
            myRadioButton.popUp()
            Spacer(modifier = Modifier.height(30.dp).fillMaxWidth())
            Text(selectedOption.value.toString())
        }
    }
}