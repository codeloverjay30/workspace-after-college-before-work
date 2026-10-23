package com.tibame.exercise.exercise7.exer7_03

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.MasterKey
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.tibame.exercise.exercise7.exer7_03.ui.theme.Exer7_03Theme
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Exer7_03Theme {
                Main()
            }
        }
    }
}


@Composable
fun Main() {
    val tag = "tag_Main"
    val context = LocalContext.current
    // 內部、外部一般檔案只差別在檔案路徑
    val file = File(context.filesDir, "internal")
    val encryptedFile = getEncryptedFile(context, file)
    val lifecycleOwner = LocalLifecycleOwner.current
    var note by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        Log.d(tag, "LaunchedEffect")
        var result: String? = null

        if (file.exists()) {
            encryptedFile.openFileInput().bufferedReader()
                .useLines { lines ->
                    result = lines.fold("") { text, line -> "$text$line" }
                }
            Log.d(tag, "Notes Loaded")
        }

        result?.let {
            note = it
        }
    }
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_STOP) {
                Log.d(tag, "ON_STOP")
                if (file.exists()) {
                    file.delete()
                }
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
 * 取得可加密檔案物件
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
