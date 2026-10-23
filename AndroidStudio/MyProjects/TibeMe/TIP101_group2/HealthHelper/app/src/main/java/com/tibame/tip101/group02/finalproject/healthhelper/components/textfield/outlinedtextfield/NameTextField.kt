package com.tibame.tip101.group02.finalproject.healthhelper.components.textfield.outlinedtextfield

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import com.tibame.tip101.group02.finalproject.healthhelper.consts.stringresourcesingleton.StringResourceSingleton

@Composable
fun NameTextField(
    modifier: Modifier = Modifier,
    mutableStateValue : MutableState<String>,
    onValueChange: ((String)-> Unit) = {},
){
    OutlinedTextField(
        value = mutableStateValue.value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = { Text(StringResourceSingleton.DIARY_DIET_NAME_LABEL) },
        trailingIcon = {
            IconButton(
                onClick = {
                    mutableStateValue.value = ""
                }
            ){
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = StringResourceSingleton.CLEAR_ICON,
                )
            }
        },
    )
}