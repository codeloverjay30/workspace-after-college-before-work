package com.example.dietapp.util.components.progressindicator.determinateprogressindicator

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dietapp.componentsattr.progressindicator.circularprogressindicator.CircularProgressIndicatorAttr
import com.example.dietapp.componentsattr.progressindicator.linearprogressindicator.LinearProgressIndicatorAttr
import com.example.dietapp.enumclass.ProgressIndicatorEnum
import com.example.dietapp.util.components.progressindicator.circularprogressindicator.MyCircularIndeterminateProgressIndicator

object MyDeterminateProgressIndicatorTest {
    @Composable
    fun test1(){

        val linearProgressIndicatorAttr = LinearProgressIndicatorAttr(
            color = ProgressIndicatorDefaults.linearColor,
            trackColor = ProgressIndicatorDefaults.linearTrackColor,
            modifier = Modifier.height(30.dp).width(200.dp),
        )
        val myLinearProgressIndicator = MyDeterminateProgressIndicator(
            progressIndicatorAttr = linearProgressIndicatorAttr,
            minValue = 0u,
            maxValue = 100u,
            delayedOneTimeInMillis = 1000u,
            columnModifier = Modifier.fillMaxWidth(),
            strokeWidth = ProgressIndicatorDefaults.CircularStrokeWidth, // dummy
            progressIndicatorEnum = ProgressIndicatorEnum.Linear
        )
        myLinearProgressIndicator.popUp()
    }

    @Composable
    fun test2(){
        val circularProgressIndicatorAttr = CircularProgressIndicatorAttr(
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
            modifier = Modifier.size(20.dp),
            strokeCap = ProgressIndicatorDefaults.CircularDeterminateStrokeCap,
        )
        val myCircularIndeterminateProgressIndicator = MyDeterminateProgressIndicator(
            circularProgressIndicatorAttr,
            strokeWidth = ProgressIndicatorDefaults.CircularStrokeWidth,
            minValue = 0u,
            maxValue = 100u,
            delayedOneTimeInMillis = 1000u,
            columnModifier = Modifier.fillMaxWidth(),
            progressIndicatorEnum = ProgressIndicatorEnum.Circular
        )

        myCircularIndeterminateProgressIndicator.popUp()
    }
}