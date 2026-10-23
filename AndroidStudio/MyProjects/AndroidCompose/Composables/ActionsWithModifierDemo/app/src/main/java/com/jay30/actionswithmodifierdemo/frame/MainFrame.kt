package com.jay30.actionswithmodifierdemo.frame

import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.DraggableState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jay30.actionswithmodifierdemo.R

@Composable
fun MainFrame() {

    var newDraggedOffset by remember { mutableFloatStateOf(0f) }

    var pointerInputKey1 by remember { mutableFloatStateOf(0f) }

    var currentOrientation by remember { mutableStateOf(Orientation.Vertical) }

    val draggableState = DraggableState { delta ->
        newDraggedOffset = delta
    }

    LazyColumn(
        modifier = Modifier
            .size(400.dp, 800.dp)
            .border(width = 3.dp, color = Color.Blue)
    ) {
        item {
            LazyRow(
                modifier = Modifier
                    .size(400.dp, 400.dp)
                    .border(width = 3.dp, color = Color.Blue)
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .size(200.dp, 200.dp)
                            .border(width = 3.dp, color = Color.Black)
                            .draggable(
                                state = draggableState,
                                orientation = if (currentOrientation == Orientation.Vertical) Orientation.Horizontal else Orientation.Vertical,
                            ),
                    ) {
                        Text(stringResource(R.string.draggable))
                    }
                }
                item {
                    Box(
                        modifier = Modifier
                            .size(200.dp, 200.dp)
                            .border(width = 3.dp, color = Color.Black)
                            .pointerInput(pointerInputKey1) { pointerInputKey1++ },
                    ) {
                        Text(stringResource(R.string.pointerInput))
                    }
                }
            }
        }
        item {
            LazyColumn (
                modifier = Modifier
                    .size(400.dp, 400.dp)
            ) {
                item {
                    Text("newDraggedOffset:${newDraggedOffset}")
                    Text("pointerInputKey1:${pointerInputKey1}")
                }
            }
        }
    }
}