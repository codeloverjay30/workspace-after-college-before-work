package com.example.dietapp.util.components.progressindicator.indeterminateprogressindicator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dietapp.componentsattr.progressindicator.circularprogressindicator.CircularProgressIndicatorAttr
import com.example.dietapp.componentsattr.progressindicator.linearprogressindicator.LinearProgressIndicatorAttr
import com.example.dietapp.enumclass.ProgressIndicatorEnum

object MyIndeterminateProgressIndicatorTest {
    @Composable
    fun test1(){

        val circularProgressIndicatorAttr = CircularProgressIndicatorAttr(
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
            modifier = Modifier.size(20.dp),
            strokeCap = ProgressIndicatorDefaults.CircularDeterminateStrokeCap,
        )
        val myIndeterminateProgressIndicator = MyIndeterminateProgressIndicator(
            circularProgressIndicatorAttr,
            strokeWidth = ProgressIndicatorDefaults.CircularStrokeWidth,
            progressIndicatorEnum = ProgressIndicatorEnum.Circular,
        )

        Column(
            modifier = Modifier.fillMaxWidth()
        ){
            myIndeterminateProgressIndicator.popUp()
        }
    }

    @Composable
    fun test2(){
        val linearProgressIndicatorAttr = LinearProgressIndicatorAttr(
            color = ProgressIndicatorDefaults.linearColor,
            trackColor = ProgressIndicatorDefaults.linearTrackColor,
            modifier = Modifier.size(20.dp),
        )
        val myIndeterminateProgressIndicator = MyIndeterminateProgressIndicator(
            linearProgressIndicatorAttr,
            strokeWidth = ProgressIndicatorDefaults.CircularStrokeWidth, // dummy
            progressIndicatorEnum = ProgressIndicatorEnum.Linear,
        )

        Column(
            modifier = Modifier.fillMaxWidth()
        ){
            myIndeterminateProgressIndicator.popUp()
        }

    }

    @Composable
    fun test3(){

        val circularProgressIndicatorAttr = CircularProgressIndicatorAttr(
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
            modifier = Modifier.size(20.dp),
            strokeCap = ProgressIndicatorDefaults.CircularDeterminateStrokeCap,
        )
        val myIndeterminateProgressIndicator = MyIndeterminateProgressIndicator(
            circularProgressIndicatorAttr,
            strokeWidth = ProgressIndicatorDefaults.CircularStrokeWidth, // dummy
            progressIndicatorEnum = ProgressIndicatorEnum.Linear,
        )

        Column(
            modifier = Modifier.fillMaxWidth()
        ){
            myIndeterminateProgressIndicator.popUp()
        }
    }

    @Composable
    fun test4(){
        val linearProgressIndicatorAttr = LinearProgressIndicatorAttr(
            color = ProgressIndicatorDefaults.linearColor,
            trackColor = ProgressIndicatorDefaults.linearTrackColor,
            modifier = Modifier.size(20.dp),
        )
        val myIndeterminateProgressIndicator = MyIndeterminateProgressIndicator(
            linearProgressIndicatorAttr,
            strokeWidth = ProgressIndicatorDefaults.CircularStrokeWidth, // dummy
            progressIndicatorEnum = ProgressIndicatorEnum.Circular,
        )

        Column(
            modifier = Modifier.fillMaxWidth()
        ){
            myIndeterminateProgressIndicator.popUp()
        }

    }
}