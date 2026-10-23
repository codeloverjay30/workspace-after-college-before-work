package com.jay30.jetchartdemo1.component.divider

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun JetDivider() {
    HorizontalDivider(modifier = Modifier.padding(horizontal = 5.dp, vertical = 50.dp), thickness = 1.dp, color = Color.Blue)
}