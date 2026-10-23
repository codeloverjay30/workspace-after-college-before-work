package com.jay30.draganddropsourcedemo2

import android.content.ClipData
import android.content.Context
import android.view.View
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.draganddrop.dragAndDropSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropTransferData
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DragAndDropSourceDemo2Frame(
    context: Context,
){
    val label = remember { "Drag me" }

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
                        DragAndDropTransferData(
                            clipData = ClipData.newPlainText(label, label),
                            flags = View.DRAG_FLAG_GLOBAL,
                        )
                    }

            ) {
                Text(modifier = Modifier.align(Alignment.Center), text = label)
            }
        }
    }
}