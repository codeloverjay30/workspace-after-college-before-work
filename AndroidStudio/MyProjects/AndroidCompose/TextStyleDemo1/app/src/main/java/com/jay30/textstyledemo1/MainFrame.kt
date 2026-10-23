package com.jay30.textstyledemo1

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.unit.sp

@Composable
fun MainFrame(){
    val textStyle =
        TextStyle(
            textAlign = TextAlign.Justify,
            lineHeight = 20.sp,
            textIndent = TextIndent(firstLine = 14.sp, restLine = 3.sp)
        )
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            Text(
                text =
                "Lorem ipsum dolor sit amet\n, consectetur adipiscing elit\n, sed do eiusmod tempor " +
                        "incididunt ut labore et dolore magna aliqua.\nUt enim ad minim veniam, quis " +
                        "nostrud exercitation ullamco laboris nisi ut\naliquip ex ea commodo consequat.",
                style = textStyle,
                fontSize = 10.sp,
            )
        }
    }
}