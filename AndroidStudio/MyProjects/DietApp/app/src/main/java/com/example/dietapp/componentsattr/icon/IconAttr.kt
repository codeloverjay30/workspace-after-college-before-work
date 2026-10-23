package com.example.dietapp.componentsattr.icon

import androidx.compose.ui.Modifier

data class IconAttr(
    val id:Int,
    val contentDescription:String = "",
    val modifier: Modifier = Modifier,
)