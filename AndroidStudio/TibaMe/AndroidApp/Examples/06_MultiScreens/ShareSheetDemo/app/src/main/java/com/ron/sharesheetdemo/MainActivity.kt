package com.ron.sharesheetdemo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.ron.sharesheetdemo.ui.theme.ShareSheetDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShareSheetDemoTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    val context = LocalContext.current
    var input by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = input,
            onValueChange = { input = it },
            placeholder = { Text(text = stringResource(id = R.string.url)) })
        Spacer(modifier = Modifier.height(16.dp))
        // 點擊按鈕後分享輸入的網址
        Button(onClick = { showSharesheet(context, input) }) {
            Text(text = stringResource(id = R.string.share))
        }
    }
}

fun showSharesheet(context: Context, input: String) {
    // 建立目的Intent物件，採用apply可在區塊內省略Intent物件名稱
    val sendIntent: Intent = Intent().apply {
        // 設定Intent動作為發送資料
        action = Intent.ACTION_SEND
        // 設定要附帶的資料
        putExtra(Intent.EXTRA_TEXT, input)
        // 設定資料的類型
        type = "text/plain"
    }

    /* 將上述目的Intent包裝成ACTION_CHOOSER Intent，
        這樣可以在分享資料前先跳出share sheet，讓使用者可挑選欲分享的App */
    val shareIntent = Intent.createChooser(sendIntent, null)
    // 依照shareIntent會開啟share sheet讓使用者挑選App
    startActivity(context, shareIntent, null)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ShareSheetDemoTheme {
        Main()
    }
}