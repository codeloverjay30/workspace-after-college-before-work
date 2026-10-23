package com.tibame.tip101.group02.finalproject.healthhelper.components.attr.dropdownmenu

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.ui.Modifier

data class DropdownMenuAttr(
    val onDismissRequest:()->Unit,
    val modifier: Modifier = Modifier.wrapContentSize()
)