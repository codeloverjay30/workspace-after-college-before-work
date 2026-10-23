package com.jay30.mutableinteractionsourcedemo1

import android.content.Context
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MutableInteractionSourceDemo1Frame(
    context: Context,
){
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isPressedMessage by remember { mutableStateOf(if(isPressed) context.getString(R.string.pressed) else context.getString(R.string.not_pressed)) }

    LazyColumn {
        item{
            // This InteractionSource will emit hover interactions
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .border(width = 3.dp,color = Color.Blue)
                    .hoverable(interactionSource = interactionSource)
                    .focusable(interactionSource = interactionSource),
                contentAlignment = Alignment.Center
            ) {
                Text(isPressedMessage)
            }
        }
    }
}