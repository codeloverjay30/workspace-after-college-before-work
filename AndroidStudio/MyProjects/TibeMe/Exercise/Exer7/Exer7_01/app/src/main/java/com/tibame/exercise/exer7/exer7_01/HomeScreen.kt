package com.tibame.exercise.exer7.exer7_01

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tibame.exercise.exer7.exer7_01.ui.theme.Ex7_01Theme

@Composable
fun HomeScreen() {
    val context = LocalContext.current
    val preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
    var colorIndex by remember { mutableIntStateOf(0) }
    var sizeIndex by remember { mutableIntStateOf(0) }
    val fileName = "android_intro.txt"
    var text by remember { mutableStateOf("") }

    val tag = "tag_HomeScreen"
    LaunchedEffect(Unit) {
        Log.d(tag, "LaunchedEffect")
        text = AssetsHandler.readAssetFile(context, fileName)
        colorIndex = preferences.getInt("colorIndex", 0)
        sizeIndex = preferences.getInt("sizeIndex", 0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            color = parseFontColor(colorIndex),
            fontSize = parseFontSize(sizeIndex).sp
        )
    }
}



@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    Ex7_01Theme {
        HomeScreen()
    }
}