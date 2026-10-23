package com.jay30.composeswipeboxdemo3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kevinnzou.compose.swipebox.SwipeBox
import com.kevinnzou.compose.swipebox.SwipeDirection
import com.kevinnzou.compose.swipebox.widget.SwipeText
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeBoxWithText() {
    val coroutineScope = rememberCoroutineScope()
    SwipeBox(
        modifier = Modifier.fillMaxWidth(),
        swipeDirection = SwipeDirection.EndToStart,
        endContentWidth = 120.dp,
        endContent = { swipeableState, endSwipeProgress ->
            SwipeText(background = Color(0xFFFFB133),
                weight = 1f,
                onClick = {
                    coroutineScope.launch {
                        swipeableState.animateTo(0)
                    }
                }) {
                Text(
                    text = "收藏", fontSize = 12.sp, maxLines = 1,
                    textAlign = TextAlign.Center, color = Color.White, fontWeight = FontWeight.Bold,
                )
            }
            SwipeText(background = Color(0xFFFA1E32),
                weight = 1f,
                onClick = {
                    coroutineScope.launch {
                        swipeableState.animateTo(0)
                    }
                }) {
                Text(
                    text = "删除", fontSize = 12.sp, maxLines = 1,
                    textAlign = TextAlign.Center, color = Color.White, fontWeight = FontWeight.Bold,
                )
            }
        }
    ) { _, _, _ ->

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .background(Color(148, 184, 216)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Swipe Multiple Directions", color = Color.White, fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}