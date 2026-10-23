package com.jay30.mutableinteractionsourcedemo3

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Indication
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MutableInteractionSourceDemo3Frame(
    context: Context,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val interactions = remember { mutableStateListOf<Interaction>() }

    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            when (interaction) {
                is PressInteraction.Press -> {
                    interactions.add(interaction)
                }

                is PressInteraction.Release -> {
                    interactions.remove(interaction.press)
                }

                is PressInteraction.Cancel -> {
                    interactions.remove(interaction.press)
                }

                is DragInteraction.Start -> {
                    interactions.add(interaction)
                }

                is DragInteraction.Stop -> {
                    interactions.remove(interaction.start)
                }

                is DragInteraction.Cancel -> {
                    interactions.remove(interaction.start)
                }
            }
        }
    }
    
    LazyColumn {
        item {
            Box(
                modifier = Modifier
                    .size(400.dp, 800.dp)
                    .border(width = 3.dp, color = Color.Blue)
                    .clickable(
                        onClick = {
                            Toast
                                .makeText(
                                    context,
                                    interactions.elemsToStrings(),
                                    Toast.LENGTH_SHORT
                                )
                                .show()
                        },
                        interactionSource = interactionSource,

                        // Also show a ripple effect
                        indication = ripple()
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = interactions.elemsToStrings(),
                    modifier = Modifier.padding(10.dp),
                )
            }
        }
    }
}

fun ripple(): Indication? {
    return null
}

fun <T> SnapshotStateList<T>.elemsToStrings(): String {
    val stringBuilder = StringBuilder()
    stringBuilder.append("[")
    this.forEach {
        stringBuilder.append(it.toString())
        stringBuilder.append("\n")
    }
    stringBuilder.append("]")
    return stringBuilder.toString()
}