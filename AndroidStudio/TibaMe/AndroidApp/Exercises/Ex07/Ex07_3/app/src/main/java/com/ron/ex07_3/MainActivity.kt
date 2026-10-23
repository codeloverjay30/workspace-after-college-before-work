package com.ron.ex07_3

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.MasterKey
import com.ron.ex07_3.ui.theme.Ex07_3Theme
import java.io.File

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Ex07_3Theme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    val context = LocalContext.current
    // 內部、外部一般檔案只差別在檔案路徑
    val file = File(context.filesDir, "internal")
    // null則代表直接存放在根目錄
    // val file = File(context.getExternalFilesDir(null), "external")

    val encryptedFile = getEncryptedFile(context, file)
    var note by remember { mutableStateOf("") }
    val lifecycleOwner = LocalLifecycleOwner.current
    val tag = "tag_Main"
    // 第一次進入composition(initial composition)時執行
    LaunchedEffect(Unit) {
        Log.d(tag, "LaunchedEffect")
        var result: String? = null
        // 載入加密檔案
        if (file.exists()) {
            encryptedFile.openFileInput().bufferedReader()
                .useLines { lines ->
                    result = lines.fold("") { text, line -> "$text$line" }
                }
            Log.d(tag, "Notes Loaded")
        }

        // 結果不為null則讀取內容
        result?.let {
            note = it
        }
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            // 當composable畫面停止呈現時
            if (event == Lifecycle.Event.ON_STOP) {
                Log.d(tag, "ON_STOP")
                /* 加密檔案無法覆蓋，需要先刪除舊檔後方能存檔，否則發生執行錯誤：
                    java.io.IOException: output file already exists, please use a new file */
                if (file.exists()) {
                    file.delete()
                }
                // 存成加密檔案
                encryptedFile.openFileOutput()
                    .bufferedWriter().use {
                        it.write(note)
                    }
                Log.d(tag, "Notes Saved")
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 32.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxSize(),
            value = note,
            onValueChange = { note = it },
            label = { Text(text = stringResource(id = R.string.notes)) }
        )
    }
}

/**
 * 取得加密檔案物件
 * 注意：加密檔案不應該使用自動備份機制，因為還原時當初加密的金鑰可能已不復在而導致無法還原
 */
private fun getEncryptedFile(context: Context, file: File): EncryptedFile {
    val masterKeyAlias = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()
    return EncryptedFile.Builder(
        context,
        file, /* 加密檔案可以為內部/外部檔案，完全依照檔案路徑來決定 */
        masterKeyAlias,
        EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
    ).build()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Ex07_3Theme {
        Main()
    }
}