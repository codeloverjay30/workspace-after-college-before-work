package com.jay30.interactionsourcedemo1

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

@Composable
fun InteractionSourceDemo1Frame(
    context:Context,
){
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    LazyColumn {
        item {
            Button(
                onClick = {
                    Toast.makeText(context,if (isPressed) context.getString(R.string.pressed) else context.getString(R.string.not_pressed),Toast.LENGTH_SHORT).show()
                },
                interactionSource = interactionSource
            ) {
                Text(if (isPressed) context.getString(R.string.pressed) else context.getString(R.string.not_pressed))
            }
        }
    }
}