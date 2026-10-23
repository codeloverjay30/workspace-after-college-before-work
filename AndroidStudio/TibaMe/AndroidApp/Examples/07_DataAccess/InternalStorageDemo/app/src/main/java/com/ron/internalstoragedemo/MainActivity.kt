package com.ron.internalstoragedemo

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
import androidx.compose.runtime.LaunchedEffect
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
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.ron.internalstoragedemo.ui.theme.InternalStorageDemoTheme
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InternalStorageDemoTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    val context = LocalContext.current
    // 內部一般檔案與內部cache檔案只差別在檔案路徑
    val file = File(context.filesDir, "internal")
    // val file = File(context.cacheDir, "cache")

    var uid by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        // 載入檔案
        val result = loadInternal(file)
        // 結果不為null則解析內容，否則顯示讀取失敗訊息
        result?.let {
            val jsonObject = Gson().fromJson(result, JsonObject::class.java)
            uid = jsonObject.get("uid").asString
            password = jsonObject.get("password").asString
            message = context.getString(R.string.uid_password_loaded)
        } ?: run { message = context.getString(R.string.uid_password_load_failed) }
    }

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

                    // 存檔
                    saveInternal(file, jsonObject.toString())
                    message = context.getString(R.string.uid_password_saved)
                }
            ) {
                Text(text = stringResource(id = R.string.save))
            }
            Button(
                onClick = {
                    // 載入檔案
                    val result = loadInternal(file)
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
 * 將指定JSON資料採用內部儲存
 */
private fun saveInternal(file: File, jsonStr: String) {
    FileOutputStream(file).bufferedWriter().use {
        it.write(jsonStr)
    }
}

/**
 * 如果檔案存在，回傳讀取內容，否則回傳null
 */
private fun loadInternal(file: File): String? {
    var result: String? = null
    // 欲開啟的檔案存在才讀取內容
    if (file.exists()) {
        FileInputStream(file).bufferedReader().useLines { lines ->
            result = lines.fold("") { text, line -> "$text$line" }
        }
    }
    return result
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    InternalStorageDemoTheme {
        Main()
    }
}