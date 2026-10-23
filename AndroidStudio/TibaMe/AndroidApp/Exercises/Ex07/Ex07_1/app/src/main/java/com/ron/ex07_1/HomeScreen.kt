package com.ron.ex07_1

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
import com.ron.ex07_1.ui.theme.Ex07_1Theme

@Composable
fun HomeScreen() {
    val context = LocalContext.current
    val preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
    var colorIndex by remember { mutableIntStateOf(0) }
    var sizeIndex by remember { mutableIntStateOf(0) }
    val fileName = "android_intro.txt"
    var text by remember { mutableStateOf("") }

    val tag = "tag_HomeScreen"
    // 第一次進入composition(initial composition)時執行
    LaunchedEffect(Unit) {
        Log.d(tag, "LaunchedEffect")
        // 取得 assets 目錄內 txt 檔案內容
        text = readAssetFile(context, fileName)
        // 取出偏好設定檔內儲存的文字顏色與尺寸
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
                // 內容過長，允許垂直捲動
                .verticalScroll(rememberScrollState()),
            color = parseFontColor(colorIndex),
            fontSize = parseFontSize(sizeIndex).sp
        )
    }
}

/**
 * 讀取assets目錄內指定檔案
 */
fun readAssetFile(context: Context, fileName: String): String {
    val inputStream = context.assets.open(fileName)
    // 將實作AutoCloseable或Closeable的物件放在use區塊內，用完會自動關閉
    inputStream.bufferedReader().useLines { lines ->
        val result = lines.fold("") { accum, line ->
            // Kotlin會將回傳值再傳給參數accum，以串接所有line
            // BufferedReader.readLine()讀資料時會去除換行字元，所以顯示時要加回來
            "$accum$line\n"
        }
        return result
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    Ex07_1Theme {
        HomeScreen()
    }
}