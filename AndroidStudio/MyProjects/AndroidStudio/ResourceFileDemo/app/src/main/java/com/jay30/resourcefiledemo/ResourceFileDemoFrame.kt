package com.jay30.resourcefiledemo

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ResourceFileDemoFrame() {
    val andlso = Font(R.font.andlso, FontWeight.W300)
    val andlsoFontFamily = FontFamily(andlso)

    Scaffold(content = { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .size(400.dp, 400.dp)
                .border(width = 3.dp, color = Color.Blue),
        ) {
            item {
                Text(stringResource(R.string.bus_arrive_title))
                Text(stringResource(R.string.bus_arrive_message, 2))
                Text(stringResource(R.string.last_bus_leave_time, 23, 24))
                Text(text = stringResource(R.string.bus_arrive_title), fontFamily = andlsoFontFamily)
                Image(painterResource(R.drawable.ic_launcher_background), contentDescription = null)

            }
        }
    })
}