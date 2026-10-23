package com.jay30.mutableinteractionsourcedemo6

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun MutableInteractionSourceDemo6Frame() {
    ScaleButton(onClick = {}) {
        Icon(Icons.Filled.ShoppingCart, "")
        Spacer(Modifier.padding(10.dp))
        Text(text = "Add to cart!")
    }
}

@Composable
fun ScaleButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
    shape: Shape = CircleShape,
    content: @Composable RowScope.() -> Unit
) {
    val scaleIndication = null

    interactionSource?.let {
        Row(
            modifier = modifier
                .defaultMinSize(minWidth = 76.dp, minHeight = 48.dp)
                .clickable(
                    enabled = enabled,
                    indication = scaleIndication,
                    interactionSource = it,
                    onClick = onClick
                )
                .border(width = 2.dp, color = Color.Blue, shape = shape)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            content = content
        )
    }
}

