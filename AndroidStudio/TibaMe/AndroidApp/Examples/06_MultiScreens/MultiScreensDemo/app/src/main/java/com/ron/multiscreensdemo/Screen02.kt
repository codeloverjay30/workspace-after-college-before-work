package com.ron.multiscreensdemo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ron.multiscreensdemo.ui.theme.MultiScreensDemoTheme

@Composable
fun Screen02(
    navController: NavHostController,
    uid: String
) {
    val context = LocalContext.current
    var age by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "${stringResource(id = R.string.uid)}: $uid")
        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            label = { Text(text = stringResource(id = R.string.age)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            // 點擊Next按鈕將user ID與age資料帶至下頁
            Button(onClick = {
                // 使用者未輸入或輸入空白會顯示錯誤訊息
                if (age.isBlank()) {
                    message = context.getString(R.string.age_blank)
                    return@Button
                }
                // 切換到指定頁並傳遞資料
                navController.navigate("${Screen.Screen03.name}/${uid}/${age}")
            }) {
                Text(text = stringResource(id = R.string.next_button))
            }
            // 點擊Back按鈕回到前頁
            Button(onClick = {
                navController.popBackStack()
            }) {
                Text(text = stringResource(id = R.string.back_button))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = message)
    }
}

@Preview(showBackground = true)
@Composable
fun Screen02Preview() {
    MultiScreensDemoTheme {
        Screen02(rememberNavController(), "john")
    }
}