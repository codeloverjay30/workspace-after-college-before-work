package com.jay30.plaintooltipboxdemo1

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TooltipBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.material3.PlainTooltipBox

@Composable
fun MainFrame() {
    val context = LocalContext.current

    var shouldShowPlainTooltipBox by remember { mutableStateOf(false) }

    val toolTipBox = PlainToolTipBox()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        item {
            TextButton(
                onClick = {
                    shouldShowPlainTooltipBox = !shouldShowPlainTooltipBox
                }
            ) {
                Text(stringResource(R.string.text1))
            }
        }
    }

    LaunchedEffect(shouldShowPlainTooltipBox) {
        if (!shouldShowPlainTooltipBox) return@LaunchedEffect

        Toast.makeText(
            context,
            context.getString(R.string.should_show_plain_tooltip_box),
            Toast.LENGTH_SHORT
        ).show()


    }
}