package com.tibame.tip101.group02.finalproject.healthhelper.components.attr.button.circlebutton

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonColors
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.tibame.tip101.group02.finalproject.healthhelper.components.attr.button.basebutton.BaseButtonAttr

data class CircleButtonAttr(
    override val onClick : ()->Unit,
    override val modifier: Modifier = Modifier,
    override val contentPadding: PaddingValues = PaddingValues(0.dp),
    override val colors: ButtonColors = ButtonColors(Color.Blue, Color.White, Color.White, Color.White),
): BaseButtonAttr(
    onClick = onClick,
    shape = CircleShape,
    modifier = modifier,
    contentPadding = contentPadding,
    colors = colors
)