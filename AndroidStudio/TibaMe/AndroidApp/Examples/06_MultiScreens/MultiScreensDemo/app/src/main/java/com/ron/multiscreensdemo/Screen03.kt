package com.ron.multiscreensdemo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ron.multiscreensdemo.ui.theme.MultiScreensDemoTheme

@Composable
fun Screen03(
    navController: NavHostController,
    uid: String,
    age: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "${stringResource(id = R.string.uid)}: $uid")
        Text(text = "${stringResource(id = R.string.age)}: $age")
        Spacer(modifier = Modifier.height(32.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            // 點擊Home按鈕直接回到首頁
            Button(onClick = {
                navController.popBackStack(Screen.Screen01.name, false)
            }) {
                Text(text = stringResource(id = R.string.home_button))
            }
            // 點擊Back按鈕回到前頁
            Button(onClick = {
                navController.popBackStack()
            }) {
                Text(text = stringResource(id = R.string.back_button))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Screen03Preview() {
    MultiScreensDemoTheme {
        Screen03(
            rememberNavController(),
            uid = "john",
            age = "18"
        )
    }
}