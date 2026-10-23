package com.jay30.mutableinteractionsourcedemo5

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MutableInteractionSourceDemo5Frame(){
    LazyColumn {
        item {
            PressIconButton(
                onClick = {},
                icon = { Icon(Icons.Filled.ShoppingCart, contentDescription = null) },
                text = { Text("Add to cart") }
            )
        }
    }
}

@Composable
fun PressIconButton(
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    text: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource? = null
) {
    val isPressed = interactionSource?.collectIsPressedAsState()?.value ?: false

    interactionSource?.let {
        Button(
            onClick = onClick,
            modifier = modifier,
            interactionSource = it
        ) {
            AnimatedVisibility(visible = isPressed) {
                if (isPressed) {
                    Row {
                        icon()
                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    }
                }
            }
            text()
        }
    }
}