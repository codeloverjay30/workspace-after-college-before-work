package com.jay30.basictooltipboxdemo1

import android.widget.Toast
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.BasicTooltipBox
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberBasicTooltipState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.TooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.window.PopupPositionProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainFrame() {
    val context = LocalContext.current

    var shouldShowPlainTooltipBox by remember { mutableStateOf(false) }

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

        item{
            if (!shouldShowPlainTooltipBox) return@item

            Toast.makeText(
                context,
                context.getString(R.string.should_show_tooltip_box),
                Toast.LENGTH_SHORT
            ).show()

            BasicTooltip()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun BasicTooltip() {
    val tooltipPosition = TooltipDefaults.rememberPlainTooltipPositionProvider()
    val tooltipState = rememberBasicTooltipState(isPersistent = false)

    BasicTooltipBox(positionProvider = tooltipPosition,
        tooltip =  { Text(stringResource(R.string.text1)) } ,
        state = tooltipState) {
        IconButton(onClick = { }) {
            Icon(imageVector = Icons.Filled.Favorite,
                contentDescription = "Your icon's description")
        }
    }
}