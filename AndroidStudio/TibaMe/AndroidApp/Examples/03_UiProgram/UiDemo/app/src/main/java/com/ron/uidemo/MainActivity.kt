package com.ron.uidemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ron.uidemo.ui.theme.UiDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UiDemoTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    var uid by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            // 填滿整個寬、高空間
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            // 內部子元件的水平對齊方式，預設為 Alignment.Start
            horizontalAlignment = Alignment.End,
            // 內部子元件之間的垂直間隔空間大小
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            TextField(
                value = uid,
                onValueChange = { uid = it },
                label = { Text(text = "User ID") },
                modifier = Modifier
                    // 設定寬度
                    .width(200.dp)
            )

            TextField(
                value = password,
                onValueChange = { password = it },
                visualTransformation = PasswordVisualTransformation(),
                label = { Text(text = "Password") },
                modifier = Modifier
                    // 填滿整個寬度空間
                    .fillMaxWidth()
            )

            Row {
                // 按鈕有文字與背景色
                Button(
                    onClick = { text = "User ID: $uid\nPassword: $password\n" }
                ) {
                    Text(text = "Submit")
                }
                // 按鈕僅有文字
                TextButton(
                    onClick = { text = "" }
                ) {
                    Text(text = "Cancel")
                }
            }
        }

        // 填補空白元件
        Spacer(modifier = Modifier.height(16.dp))
        // 水平分隔線
        HorizontalDivider(color = Color.DarkGray)

        Column(
            // 內部子元件的水平對齊方式，預設為 Alignment.Start
            horizontalAlignment = Alignment.CenterHorizontally,
            // 內部子元件之間的垂直間隔空間大小
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                // 填滿整個寬、高空間
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                // 內部子元件的垂直對齊方式，預設為 Alignment.Top
                verticalAlignment = Alignment.CenterVertically,
                // 內部子元件之間的水平間隔空間大小
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .height(100.dp)
                    .background(Color.LightGray)
                    .padding(horizontal = 8.dp)
            ) {
                // 按鈕有 文字 + 背景色
                Button(
                    onClick = { text = "" },
                    colors = ButtonDefaults.buttonColors(
                        // 設定按鈕容器顏色
                        containerColor = Color.Red,
                        // 設定按鈕內容顏色
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "Clear",
                        fontSize = 20.sp
                    )
                }

                // 垂直分隔線
                VerticalDivider(color = Color.Gray)

                // 按鈕有內建圖示
                Button(onClick = { text = "" }) {
                    Icon(
                        // 使用內建圖示
                        imageVector = Icons.Default.Clear,
                        contentDescription = "clear",
                        // 設定圖片大小
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Row(
                // 內部子元件之間的水平間隔空間大小
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // 按鈕有 文字 + 自訂圖示
                Button(
                    onClick = { text = "Cat" },
                    colors = ButtonDefaults.buttonColors(
                        // 設定按鈕容器顏色
                        containerColor = Color.Transparent,
                        // 設定按鈕內容顏色
                        contentColor = Color.Blue
                    )
                ) {
                    Image(
                        // 使用指定圖檔資源
                        painter = painterResource(id = R.drawable.cat),
                        contentDescription = "cat",
                        modifier = Modifier.size(30.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Cat",
                        fontSize = 20.sp
                    )
                }

                // 按鈕有自訂圖示
                Button(
                    onClick = { text = "Cat" },
                    colors = ButtonDefaults.buttonColors(
                        // 設定按鈕容器顏色
                        containerColor = Color.Transparent,
                    )
                ) {
                    Image(
                        // 使用指定圖檔資源
                        painter = painterResource(id = R.drawable.cat),
                        contentDescription = "cat",
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }

        // 水平分隔線
        HorizontalDivider(color = Color.DarkGray)
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    UiDemoTheme {
        Main()
    }
}