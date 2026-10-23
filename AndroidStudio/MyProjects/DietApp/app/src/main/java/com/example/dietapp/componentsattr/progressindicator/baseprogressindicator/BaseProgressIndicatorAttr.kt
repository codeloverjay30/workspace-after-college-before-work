package com.example.dietapp.componentsattr.progressindicator.baseprogressindicator

import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap

open class BaseProgressIndicatorAttr (
    open val color: Color,
    open val trackColor: Color,
    open val modifier: Modifier = Modifier,
    open val strokeCap: StrokeCap = ProgressIndicatorDefaults.CircularDeterminateStrokeCap,
)