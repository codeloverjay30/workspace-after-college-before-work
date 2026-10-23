package com.ron.multiscreensdemo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ron.multiscreensdemo.ui.theme.MultiScreensDemoTheme

@Composable
fun Screen01(
    navController: NavHostController
) {
    val context = LocalContext.current
    var uid by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = uid,
            onValueChange = { uid = it },
            label = { Text(text = stringResource(id = R.string.uid)) },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))
        // 點擊Next按鈕將輸入的user ID帶至下頁
        Button(onClick = {
            // 使用者未輸入或輸入空白會顯示錯誤訊息
            if (uid.isBlank()) {
                /* stringResource()必須放在@Composable function內呼叫，
                    改用Context.getString() */
                message = context.getString(R.string.uid_blank)
                return@Button
            }
            // 切換到指定頁並傳遞資料
            navController.navigate("${Screen.Screen02.name}/$uid")
        }) {
            Text(text = stringResource(id = R.string.next_button))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = message)
    }
}

@Preview(showBackground = true)
@Composable
fun Screen01Preview() {
    MultiScreensDemoTheme {
        Screen01(rememberNavController())
    }
}
