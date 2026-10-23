package com.tibame.tip101.group02.finalproject.healthhelper.components.textfield.outlinedtextfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import com.tibame.tip101.group02.finalproject.healthhelper.components.dropdown.dropmenu.MyExposedDropDownMenu
import com.tibame.tip101.group02.finalproject.healthhelper.consts.stringresourcesingleton.StringResourceSingleton

@Composable
fun FoodTextField(
    modifier: Modifier = Modifier,
    mutableStateValue : MutableState<String>,
    onValueChange: (String)-> Unit,
){
    val options = listOf("Apple","Banana")
    MyExposedDropDownMenu(
        mutableStateValue = mutableStateValue,
        options = options,
        label = {Text(StringResourceSingleton.DIARY_DIET_FOOD_LABEL)},
        onValueChangedEvent = onValueChange,
        modifier = modifier,
    )
}