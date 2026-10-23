package com.example.dietapp.componentsattr.progressindicator.circularprogressindicator

import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import com.example.dietapp.componentsattr.progressindicator.baseprogressindicator.BaseProgressIndicatorAttr

class CircularProgressIndicatorAttr (
    override val color: Color,
    override val trackColor: Color,
    override val modifier: Modifier = Modifier,
    override val strokeCap: StrokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
): BaseProgressIndicatorAttr(
    color = color,
    trackColor = trackColor,
    modifier = modifier,
    strokeCap = strokeCap,
)