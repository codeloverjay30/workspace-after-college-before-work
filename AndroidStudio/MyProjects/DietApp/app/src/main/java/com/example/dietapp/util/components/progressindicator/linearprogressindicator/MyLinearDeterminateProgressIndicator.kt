package com.example.dietapp.util.components.progressindicator.linearprogressindicator

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dietapp.componentsattr.progressindicator.linearprogressindicator.LinearProgressIndicatorAttr
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * MyLinearDeterminateProgressIndicator:
 *
 * A wrapper class for determinate linear progress indicator. Including
 * + LinearProgressIndicator
 *
 * @property linearProgressIndicatorAttr
 * @property minValue
 * @property maxValue
 * @property delayedOneTimeInMillis
 * @property columnModifier
 * @constructor Create empty My linear determinate progress indicator
 */
class MyLinearDeterminateProgressIndicator(
    val linearProgressIndicatorAttr : LinearProgressIndicatorAttr,
    val minValue: UInt = 0u,
    val maxValue: UInt = 100u,
    val delayedOneTimeInMillis: UInt = 100u,
    val columnModifier: Modifier = Modifier.fillMaxWidth(),
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
                LinearProgressIndicator(
                    progress = { currentProgress },
                    color = linearProgressIndicatorAttr.color,
                    trackColor = linearProgressIndicatorAttr.trackColor,
                    modifier = linearProgressIndicatorAttr.modifier,
                    strokeCap = linearProgressIndicatorAttr.strokeCap,
                )
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