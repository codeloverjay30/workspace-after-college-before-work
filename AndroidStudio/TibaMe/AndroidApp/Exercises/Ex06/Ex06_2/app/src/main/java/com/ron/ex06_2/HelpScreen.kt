package com.ron.ex06_2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ron.ex06_2.ui.theme.Ex06_2Theme

@Composable
fun HelpScreen() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "This page will show you how to use this App!!")
    }
}

@Preview(showBackground = true)
@Composable
fun HelpScreenPreview() {
    Ex06_2Theme {
        HelpScreen()
    }
}