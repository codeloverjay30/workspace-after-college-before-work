package com.example.dietapp.util.components.progressindicator.determinateprogressindicator

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.dietapp.componentsattr.progressindicator.baseprogressindicator.BaseProgressIndicatorAttr
import com.example.dietapp.enumclass.ProgressIndicatorEnum
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * MyDeterminateProgressIndicator:
 *
 * A wrapper class for determinate progress indicator. Here progress indicator including
 * + LinearProgressIndicator
 * + CircularProgressIndicator
 *
 * @param T
 * @property progressIndicatorAttr
 * @property minValue
 * @property maxValue
 * @property delayedOneTimeInMillis
 * @property columnModifier
 * @property strokeWidth
 * @property progressIndicatorEnum
 * @constructor Create empty My determinate progress indicator
 */
class MyDeterminateProgressIndicator<T:BaseProgressIndicatorAttr>(
    private val progressIndicatorAttr: T,
    private val minValue: UInt = 0u,
    private val maxValue: UInt = 100u,
    private val delayedOneTimeInMillis: UInt = 100u,
    private val columnModifier: Modifier = Modifier.fillMaxWidth(),
    private val strokeWidth: Dp = ProgressIndicatorDefaults.CircularStrokeWidth,
    private val progressIndicatorEnum: ProgressIndicatorEnum,
){
    /**
     * Pop up the components
     */
    @SuppressLint("CoroutineCreationDuringComposition")
    @Composable
    fun popUp() {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = columnModifier
        ) {
            var currentProgress by remember { mutableStateOf(minValue.toFloat()) }
            var loading by remember { mutableStateOf(false) }
            val scope = rememberCoroutineScope() // Create a coroutine scope

            loading = true
            scope.launch {
                loadProgress { progress ->
                    currentProgress = progress
                }
                loading = false // Reset loading when the coroutine finishes
            }
            if (loading) {
                if(progressIndicatorEnum == ProgressIndicatorEnum.Linear){
                    LinearProgressIndicator(
                        progress = { currentProgress },
                        color = progressIndicatorAttr.color,
                        trackColor = progressIndicatorAttr.trackColor,
                        modifier = progressIndicatorAttr.modifier,
                        strokeCap = progressIndicatorAttr.strokeCap,
                    )
                }
                else if(progressIndicatorEnum == ProgressIndicatorEnum.Circular) {
                    CircularProgressIndicator(
                        progress = { currentProgress },
                        color = progressIndicatorAttr.color,
                        trackColor = progressIndicatorAttr.trackColor,
                        modifier = progressIndicatorAttr.modifier,
                        strokeCap = progressIndicatorAttr.strokeCap,
                        strokeWidth = strokeWidth,
                    )
                }
            }
        }
    }

    /**
     * Load progress
     *
     * @param updateProgress
     * @receiver
     */
    suspend fun loadProgress(updateProgress: (Float) -> Unit) {
        for (i in minValue.toInt()+1 .. maxValue.toInt()) {
            updateProgress(i.toFloat() / maxValue.toInt())
            delay(delayedOneTimeInMillis.toLong())
        }
    }

}