package com.tibame.tip101.group02.finalproject.healthhelper.components.attr.button.basebutton

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonColors
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

open class BaseButtonAttr(
    open val onClick : ()->Unit,
    open val shape: Shape = CircleShape,
    open val modifier: Modifier = Modifier,
    open val contentPadding: PaddingValues = PaddingValues(0.dp),
    open val colors: ButtonColors = ButtonColors(Color.Blue,Color.White,Color.White,Color.White),
)