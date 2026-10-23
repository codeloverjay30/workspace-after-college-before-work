package com.ron.assetsdemo

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ron.assetsdemo.ui.theme.AssetsDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AssetsDemoTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    val context = LocalContext.current
    val fileName = "android_intro.txt"
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row {
            Button(onClick = { text = readAssetFile(context, fileName) }) {
                Text(text = stringResource(id = R.string.open_file))
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { text = "" }) {
                Text(text = stringResource(id = R.string.clear))
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = text,
            modifier = Modifier
                .fillMaxSize()
                // 內容過長，允許垂直捲動
                .verticalScroll(rememberScrollState())
        )
    }
}

fun readAssetFile(context: Context, fileName: String): String {
    // 設定讀取assets目錄內指定檔案
    val inputStream = context.assets.open(fileName)
    // 將實作AutoCloseable或Closeable的物件放在use區塊內，用完會自動關閉
    inputStream.bufferedReader().useLines { lines ->
        // 累加每行文字，初始值需設為空字串""
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
fun DefaultPreview() {
    AssetsDemoTheme {
        Main()
    }
}