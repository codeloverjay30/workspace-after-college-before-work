package com.ron.ex07_2

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.ron.ex07_2.ui.theme.Ex07_2Theme
import java.io.File

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Ex07_2Theme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    val context = LocalContext.current
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    val gson = Gson()

    val tag = "tag_Main"
    LaunchedEffect(Unit) {
        Log.d(tag,"LaunchedEffect")
        val result = loadInternal(context)
        result?.let {
            val jsonObject = gson.fromJson(result, JsonObject::class.java)
            height = jsonObject.get("height").asString
            weight = jsonObject.get("weight").asString
            message = Bmi(height.toDouble(), weight.toDouble()).toString()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(0.6f),
            value = height,
            onValueChange = { height = it },
            label = { Text(text = stringResource(id = R.string.height)) }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(0.6f),
            value = weight,
            onValueChange = { weight = it },
            label = { Text(text = stringResource(id = R.string.weight)) }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            modifier = Modifier.fillMaxWidth(0.6f),
            onClick = {
                val bmi = Bmi(height.toDouble(), weight.toDouble())
                saveInternal(context, gson.toJson(bmi))
                message = bmi.toString()
                Toast.makeText(context, "Data saved", Toast.LENGTH_SHORT).show()
            }
        ) {
            Text(text = stringResource(id = R.string.calculate_bmi))
        }
        Button(
            modifier = Modifier.fillMaxWidth(0.6f),
            onClick = {
                height = ""
                weight = ""
                message = ""
            }
        ) {
            Text(text = stringResource(id = R.string.clear))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = message)
    }
}

private fun saveInternal(context: Context, jsonStr: String) {
    context.openFileOutput("internal", Context.MODE_PRIVATE)
        .bufferedWriter().use {
            it.write(jsonStr)
        }
}

/**
 * 如果檔案存在，回傳讀取內容，否則回傳null
 */
private fun loadInternal(context: Context): String? {
    // filesDir可以取得內部儲存目錄
    val file = File(context.filesDir, "internal")
    var result: String? = null
    // 欲開啟的檔案存在才讀取內容
    if (file.exists()) {
        context.openFileInput("internal").bufferedReader().useLines { lines ->
            result = lines.fold("") { text, line -> "$text$line" }
        }
    }
    return result
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Ex07_2Theme {
        Main()
    }
}