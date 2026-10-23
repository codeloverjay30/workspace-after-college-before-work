package com.example.dietapp.util.components.progressindicator.linearprogressindicator

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dietapp.componentsattr.progressindicator.linearprogressindicator.LinearProgressIndicatorAttr

object MyLinearProgressIndicatorTest {
    @Composable
    fun test1(){

        val linearProgressIndicatorAttr = LinearProgressIndicatorAttr(
            color = ProgressIndicatorDefaults.linearColor,
            trackColor = ProgressIndicatorDefaults.linearTrackColor,
            modifier = Modifier.height(30.dp).width(200.dp),
        )
        val myLinearProgressIndicator = MyLinearDeterminateProgressIndicator(
            linearProgressIndicatorAttr = linearProgressIndicatorAttr,
            minValue = 0u,
            maxValue = 100u,
            delayedOneTimeInMillis = 1000u,
            columnModifier = Modifier.fillMaxWidth(),
        )
        myLinearProgressIndicator.popUp()
    }
}