package com.jay30.draganddropsourcedemo1

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.draganddrop.dragAndDropSource
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DragAndDropSourceDemo1Frame(
    context: Context,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .border(width = 3.dp, color = Color.Blue)
    ) {
        item {
            // to detect a long press for pointer input is implemented as follows:
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .border(width = 3.dp, color = Color.Red)
                    .dragAndDropSource {
                        detectTapGestures(onLongPress = {
                            Toast
                                .makeText(
                                    context,
                                    context.getString(R.string.detect_pressed),
                                    Toast.LENGTH_SHORT
                                )
                                .show()
                        })
                    }

            ) {
                Text("A box")
            }
        }
    }
}