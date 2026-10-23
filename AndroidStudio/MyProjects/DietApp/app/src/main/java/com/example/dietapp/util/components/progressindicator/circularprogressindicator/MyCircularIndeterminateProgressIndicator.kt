package com.example.dietapp.util.components.progressindicator.circularprogressindicator

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.Dp
import com.example.dietapp.componentsattr.progressindicator.circularprogressindicator.CircularProgressIndicatorAttr

/**
 * MyCircularIndeterminateProgressIndicator:
 *
 * A wrapper class for indeterminate circular progress indicator. Including
 * + CircularProgressIndicator
 *
 * @property circularProgressIndicatorAttr
 * @property strokeWidth
 * @constructor Create empty My circular indeterminate progress indicator
 */

class MyCircularIndeterminateProgressIndicator(
    private val circularProgressIndicatorAttr: CircularProgressIndicatorAttr,
    private val strokeWidth: Dp = ProgressIndicatorDefaults.CircularStrokeWidth,
){

    /**
     * Pop up the components
     */
    @Composable
    fun popUp() {
        var loading by remember { mutableStateOf(false) }

        loading = true
        if (!loading) return

        CircularProgressIndicator(
            color = circularProgressIndicatorAttr.color,
            modifier = circularProgressIndicatorAttr.modifier,
            trackColor = circularProgressIndicatorAttr.trackColor,
            strokeCap = circularProgressIndicatorAttr.strokeCap,
            strokeWidth = strokeWidth,
        )
    }
}