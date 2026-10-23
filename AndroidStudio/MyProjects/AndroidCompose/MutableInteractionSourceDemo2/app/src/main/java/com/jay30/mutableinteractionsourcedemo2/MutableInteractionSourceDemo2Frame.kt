package com.jay30.mutableinteractionsourcedemo2

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Indication
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MutableInteractionSourceDemo2Frame(
    context: Context,
){
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    LazyColumn {
        item {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .border(width = 3.dp,color = Color.Blue)
                    .clickable(
                        onClick = {
                            Toast.makeText(context,if (isPressed) context.getString(R.string.pressed) else context.getString(R.string.not_pressed),Toast.LENGTH_SHORT).show()
                        },
                        interactionSource = interactionSource,

                        // Also show a ripple effect
                        indication = ripple()
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(if (isPressed) context.getString(R.string.pressed) else context.getString(R.string.not_pressed))
            }
        }
    }
}

fun ripple(): Indication?{
    return null
}
