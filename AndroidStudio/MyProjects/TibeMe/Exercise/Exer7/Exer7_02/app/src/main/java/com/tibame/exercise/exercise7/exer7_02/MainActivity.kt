package com.tibame.exercise.exercise7.exer7_02

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.tibame.exercise.exercise7.exer7_02.ui.theme.Exer7_02Theme
import com.tibame.exercise.exercise7.exer7_02.util.bmi.BmiUtil
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Exer7_02Theme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    val context = LocalContext.current
    val file = File(context.filesDir, "internal")

    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var bmi by remember { mutableStateOf(0f) }
    var message by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        val result = loadInternal(file)
        result?.let {
            val jsonObject = Gson().fromJson(result, JsonObject::class.java)
            height = jsonObject.get("height").asString
            weight = jsonObject.get("weight").asString
            bmi = jsonObject.get("bmi").asFloat
            message = jsonObject.get("healthStatus").asString
        } ?: run { message = context.getString(R.string.height_load_failed) }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            value = height,
            onValueChange = { height = it },
            label = { Text(text = stringResource(id = R.string.height)) },
            singleLine = true
        )
        TextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text(text = stringResource(id = R.string.weight)) },
            singleLine = true,
        )
        Button(
            onClick = {
                if(!BmiUtil.isValid(weight,height)){
                    message = "Error!!! Invalid input"
                    return@Button
                }
                bmi = BmiUtil.calculateBMI(weight.toInt(),height.toInt())
                message = BmiUtil.getHealthStatus(bmi)

                val jsonObject = JsonObject()
                jsonObject.addProperty("height", height)
                jsonObject.addProperty("weight", weight)
                jsonObject.addProperty("bmi", bmi)
                jsonObject.addProperty("healthStatus",message)
                saveInternal(file, jsonObject.toString())

                Toast.makeText(
                    context,
                    context.getString(R.string.data_saved),
                    Toast.LENGTH_SHORT,
                ).show()
            }
        ) {
            Text(text = stringResource(id = R.string.calculate_bmi_button))
        }

        Button(
            onClick = {
                height = ""
                weight = ""
                bmi = 0f
                message = ""
            }
        ) {
            Text(stringResource(R.string.clear))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text("Height:${height}")
        Text("Weight:${weight}")
        Text("BMI:${bmi}")
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
    if (file.exists()) {
        FileInputStream(file).bufferedReader().useLines { lines ->
            result = lines.fold("") { text, line -> "$text$line" }
        }
    }
    return result
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    Exer7_02Theme {
        Main()
    }
}