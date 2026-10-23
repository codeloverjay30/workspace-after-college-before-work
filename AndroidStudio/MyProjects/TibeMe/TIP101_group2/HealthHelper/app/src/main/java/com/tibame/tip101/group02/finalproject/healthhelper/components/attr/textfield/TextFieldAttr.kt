package com.tibame.tip101.group02.finalproject.healthhelper.components.attr.textfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

data class TextFieldAttr(
    val placeholder:@Composable ()->Unit,
    val label:@Composable () -> Unit,
    var value: String,
    val onValueChange: (String)-> Unit,
    val modifier: Modifier = Modifier.fillMaxWidth()
)