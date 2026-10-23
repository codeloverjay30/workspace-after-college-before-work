package com.ron.ex06_1

import androidx.compose.foundation.layout.Column
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
import com.ron.ex06_1.ui.theme.Ex06_1Theme

@Composable
fun BMI01(
    navController: NavHostController
) {
    val context = LocalContext.current
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = height,
            onValueChange = { height = it },
            label = { Text(text = stringResource(id = R.string.height)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )
        OutlinedTextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text(text = stringResource(id = R.string.weight)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))
        // 點擊Submit按鈕將height與weight資料帶至下頁
        Button(onClick = {
            // 使用者未輸入或輸入空白會顯示錯誤訊息
            if (height.isBlank() || weight.isBlank()) {
                message = context.getString(R.string.height_weight_blank)
                return@Button
            }
            // 切換到指定頁並傳遞資料
            navController.navigate("${Screen.BMI02.name}/$height/$weight")
        }) {
            Text(text = stringResource(id = R.string.submit_button))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = message)
    }
}

@Preview(showBackground = true)
@Composable
fun Screen01Preview() {
    Ex06_1Theme {
        BMI01(rememberNavController())
    }
}
