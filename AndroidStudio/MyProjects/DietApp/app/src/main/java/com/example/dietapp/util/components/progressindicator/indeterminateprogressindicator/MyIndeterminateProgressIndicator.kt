package com.example.dietapp.util.components.progressindicator.indeterminateprogressindicator

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.Dp
import com.example.dietapp.componentsattr.progressindicator.baseprogressindicator.BaseProgressIndicatorAttr
import com.example.dietapp.enumclass.ProgressIndicatorEnum

/**
 * MyIndeterminateProgressIndicator
 *
 * A wrapper class for indeterminate progress indicator. Here progress indicator including
 * + LinearProgressIndicator
 * + CircularProgressIndicator
 *
 * @param T
 * @property progressIndicatorAttr
 * @property strokeWidth
 * @property progressIndicatorEnum
 * @constructor Create empty My indeterminate progress indicator
 */
class MyIndeterminateProgressIndicator<T: BaseProgressIndicatorAttr>(
    private val progressIndicatorAttr: T,
    private val strokeWidth: Dp = ProgressIndicatorDefaults.CircularStrokeWidth,
    private val progressIndicatorEnum: ProgressIndicatorEnum,
){

    /**
     * Pop up the components
     */
    @Composable
    fun popUp() {
        var loading by remember { mutableStateOf(false) }

        loading = true
        if (!loading) return

        if(progressIndicatorEnum == ProgressIndicatorEnum.Linear) {
            LinearProgressIndicator(
                color = progressIndicatorAttr.color,
                modifier = progressIndicatorAttr.modifier,
                trackColor = progressIndicatorAttr.trackColor,
                strokeCap = progressIndicatorAttr.strokeCap,
            )
        }else if(progressIndicatorEnum == ProgressIndicatorEnum.Circular){
            CircularProgressIndicator(
                color = progressIndicatorAttr.color,
                modifier = progressIndicatorAttr.modifier,
                trackColor = progressIndicatorAttr.trackColor,
                strokeCap = progressIndicatorAttr.strokeCap,
                strokeWidth = strokeWidth
            )
        }else{
            throw Exception("")
        }
    }
}