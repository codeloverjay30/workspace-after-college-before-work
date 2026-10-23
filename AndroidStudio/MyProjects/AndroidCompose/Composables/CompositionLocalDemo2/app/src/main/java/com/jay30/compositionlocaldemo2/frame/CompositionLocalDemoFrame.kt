package com.jay30.compositionlocaldemo2.frame

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.jay30.compositionlocaldemo2.R

@Composable
fun CompositionLocalDemoFrame() {
    LazyColumn(
        modifier = Modifier
            .size(400.dp, 400.dp)
            .border(width = 3.dp, color = Color.Blue),
    ) {
        item {
            CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.primary) {
                Text("Primary color provided by LocalContentColor")
                Text("This Text also uses primary as textColor")
                CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.error) {
                    DescendantExample()
                }
                QuantityStringExample(20)
            }
        }
    }
}

@Composable
fun DescendantExample() {
    // CompositionLocalProviders also work across composable functions
    Text("This Text uses the error color now")
}

@Composable
fun QuantityStringExample(size: Int) {
    // Get `resources` from the current value of LocalContext

    val resources = LocalContext.current.resources
    val text = remember(resources, size) {
        resources.getQuantityString(R.plurals.text_title, size)
    }
    /*
Text(text = text)

 */
}