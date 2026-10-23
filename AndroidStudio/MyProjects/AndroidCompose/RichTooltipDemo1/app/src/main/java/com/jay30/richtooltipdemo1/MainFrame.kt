package com.jay30.richtooltipdemo1

import android.widget.Toast
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberBasicTooltipState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RichTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MainFrame() {
    val context = LocalContext.current

    val tooltipPosition = TooltipDefaults.rememberPlainTooltipPositionProvider()
    val tooltipState = rememberTooltipState(isPersistent = false)

    val scope = rememberCoroutineScope()

    var shouldShowPlainTooltipBox by remember { mutableStateOf(false) }

    val tooltipIcon = painterResource(R.drawable.ic_launcher_background)


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

        item {
            if (!shouldShowPlainTooltipBox) return@item

            Toast.makeText(
                context,
                context.getString(R.string.should_show_tooltip_box),
                Toast.LENGTH_SHORT
            ).show()

            TooltipBox(
                positionProvider = tooltipPosition,
                tooltip = {
                    RichTooltip(
                        title = { Text(stringResource(R.string.text1)) },
                        action = {
                            TextButton(onClick = {
                                scope.launch {
                                    tooltipState.dismiss()
                                    tooltipState.onDispose()
                                }
                            }) {
                                Text(stringResource(R.string.dismiss))
                            }
                        }
                    ) {
                        Text(stringResource(R.string.tooltip_box_description_message))
                    }
                },
                state = tooltipState
            ) {
                IconButton(onClick = {

                }) {
                    Icon(
                        painter = tooltipIcon,
                        contentDescription = null,
                    )
                }
            }
        }
    }
}