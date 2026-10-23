package com.ron.preferencesdemo

import android.content.Context
import android.os.Bundle
import android.util.Log
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
import com.ron.preferencesdemo.ui.theme.PreferencesDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PreferencesDemoTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    val context = LocalContext.current
    val preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
    var uid by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    val tag = "tag_Main"
    // 第一次進入composition(initial composition)時執行
    LaunchedEffect(Unit) {
        Log.d(tag,"LaunchedEffect")
        // 取出當初儲存資料，如果沒有儲存則回傳空字串
        uid = preferences.getString("uid", "")!!
        password = preferences.getString("password", "")!!
        message = context.getString(R.string.uid_password_loaded)
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
                    preferences.edit()
                        .putString("uid", uid)
                        .putString("password", password)
                        .apply()
                    message = context.getString(R.string.uid_password_saved)
                }
            ) {
                Text(text = stringResource(id = R.string.save))
            }
            Button(
                onClick = {
                    uid = preferences.getString("uid", "")!!
                    password = preferences.getString("password", "")!!
                    message = context.getString(R.string.uid_password_loaded)
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PreferencesDemoTheme {
        Main()
    }
}