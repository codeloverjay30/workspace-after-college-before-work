package com.ron.encryptedfiledemo

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.MasterKey
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.ron.encryptedfiledemo.ui.theme.EncryptedFileDemoTheme
import java.io.File

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EncryptedFileDemoTheme {
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
    /* DIRECTORY_DOCUMENTS代表要存放在外部儲存根目錄內的標準Documents子目錄，
        null則代表直接存放在根目錄 */
//    val file = File(
//        context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),
//        "external"
//    )

    val encryptedFile = getEncryptedFile(context, file)
    var uid by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            value = uid,
            onValueChange = { uid = it },
            label = { Text(text = stringResource(id = R.string.uid)) },
            singleLine = true
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = stringResource(id = R.string.password)) },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(
                onClick = {
                    // 將資料轉成JSON
                    val jsonObject = JsonObject()
                    jsonObject.addProperty("uid", uid)
                    jsonObject.addProperty("password", password)
                    /* 加密檔案無法覆蓋，需要先刪除舊檔後方能存檔，否則發生執行錯誤：
                        java.io.IOException: output file already exists, please use a new file */
                    if (file.exists()) {
                        file.delete()
                    }
                    // 存成加密檔案
                    encryptedFile.openFileOutput()
                        .bufferedWriter().use {
                            it.write(jsonObject.toString())
                        }
                    message = context.getString(R.string.uid_password_saved)
                }
            ) {
                Text(text = stringResource(id = R.string.save))
            }
            Button(
                onClick = {
                    var result: String? = null
                    // 載入加密檔案
                    if (file.exists()) {
                        encryptedFile.openFileInput().bufferedReader()
                            .useLines { lines ->
                                result = lines.fold("") { text, line -> "$text$line" }
                            }
                    }

                    // 結果不為null則解析內容，否則顯示讀取失敗訊息
                    result?.let {
                        val jsonObject = Gson().fromJson(result, JsonObject::class.java)
                        uid = jsonObject.get("uid").asString
                        password = jsonObject.get("password").asString
                        message = context.getString(R.string.uid_password_loaded)
                    } ?: run { message = context.getString(R.string.uid_password_load_failed) }
                }
            ) {
                Text(text = stringResource(id = R.string.load))
            }
            Button(
                onClick = {
                    uid = ""
                    password = ""
                    message = ""
                }
            ) {
                Text(text = stringResource(id = R.string.clear))
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = message)
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EncryptedFileDemoTheme {
        Main()
    }
}