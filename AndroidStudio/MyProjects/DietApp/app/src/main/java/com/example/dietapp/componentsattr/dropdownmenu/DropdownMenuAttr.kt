package com.example.dietapp.componentsattr.dropdownmenu

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.ui.Modifier

data class DropdownMenuAttr(
    val onDismissRequest:()->Unit,
    val modifier: Modifier = Modifier.wrapContentSize()
)