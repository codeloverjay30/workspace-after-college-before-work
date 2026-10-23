package com.jay30.paragraphstyledemo1

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.Hyphens
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.unit.sp

@Composable
fun MainFrame() {
    val text =
        "Lorem ipsum dolor sit amet\n, consectetur adipiscing elit\n, sed do eiusmod tempor " +
                "incididunt ut labore et dolore magna aliqua.\nUt enim ad minim veniam, quis\n" +
                "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."

    val paragraphStyle1 = ParagraphStyle(
        lineHeight = 30.sp,
        hyphens = Hyphens.Auto
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ){
        item {
            Text(
                text =
                buildAnnotatedString {
                    append(text)
                    addStyle(paragraphStyle1, 0 , text.length)
                },
                fontSize = 10.sp
            )
        }
    }
}