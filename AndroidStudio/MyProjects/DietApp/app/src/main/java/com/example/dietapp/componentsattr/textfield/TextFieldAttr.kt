package com.example.dietapp.componentsattr.textfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue

data class TextFieldAttr(
    val placeholder:@Composable ()->Unit,
    val label:@Composable () -> Unit,
    var value:TextFieldValue,
    val onValueChange: (TextFieldValue)-> Unit,
    val modifier: Modifier = Modifier.fillMaxWidth()
)