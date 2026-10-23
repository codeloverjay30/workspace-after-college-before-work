package com.example.dietapp.util.components.progressindicator.circularprogressindicator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dietapp.componentsattr.progressindicator.circularprogressindicator.CircularProgressIndicatorAttr

object MyCircularIndeterminateProgressIndicatorTest {
    @Composable
    fun test1(){

        val circularProgressIndicatorAttr = CircularProgressIndicatorAttr(
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
            modifier = Modifier.size(20.dp),
            strokeCap = ProgressIndicatorDefaults.CircularDeterminateStrokeCap,
        )
        val myCircularIndeterminateProgressIndicator = MyCircularIndeterminateProgressIndicator(
            circularProgressIndicatorAttr,
            strokeWidth = ProgressIndicatorDefaults.CircularStrokeWidth,
        )

        Column(
            modifier = Modifier.fillMaxWidth()
        ){
            myCircularIndeterminateProgressIndicator.popUp()
        }
    }
}