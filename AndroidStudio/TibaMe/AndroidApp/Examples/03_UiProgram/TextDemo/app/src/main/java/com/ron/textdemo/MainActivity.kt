package com.ron.textdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ron.textdemo.ui.theme.TextDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TextDemoTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    var uid by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    Column(
        // 內容物水平置中
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp) // 所有padding皆為16
        // .padding(top = 64.dp)
        // .padding(horizontal = 16.dp, vertical = 32.dp)
        // .padding(start = 16.dp, top = 64.dp, end = 8.dp, bottom = 32.dp)
    ) {
        Text(
            text = "Form",
            fontSize = 30.sp, // 字型大小: 30
            fontWeight = FontWeight.Bold, // 字體樣式: 粗體
            color = Color.Blue // 字的顏色: 藍色
        )

        TextField(
            value = uid,
            // 輸入的值改變時呼叫此方法，並將值傳給自動宣告的變數"it"
            onValueChange = { uid = it },
            // 提示文字
            label = { Text(text = "User ID") },
            // 最多一列
            singleLine = true,
            // 形狀設定為圓角矩形
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )

        // 有外框文字輸入方塊
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            // placeholder提示文字不會在點擊時上移，與label不同
            placeholder = { Text(text = "Password") },
            // 設定開頭圖示
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "password"
                )
            },
            // 設定結尾圖示
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "clear",
                    // 可設定該元件點擊時要執行指定程式
                    modifier = Modifier.clickable {
                        password = ""
                    }
                )
            },
            shape = RoundedCornerShape(16.dp), // 圓角矩形
            // 密碼樣式
            visualTransformation = PasswordVisualTransformation(),
            // 鍵盤種類為密碼
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            // 預覽畫面如果看不到顏色變化，請改用模擬器
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Blue,
                unfocusedIndicatorColor = Color.Gray,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )

        // 輸入的文字對齊末端
        TextField(
            value = phone,
            // 輸入的值改變時呼叫此方法
            onValueChange = { phone = it },
            // 提示文字
            label = { Text(text = "Phone number") },
            // 輸入的文字對齊末端
            textStyle = TextStyle(textAlign = TextAlign.End),
            singleLine = true,
            // 鍵盤種類為電話
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )

        Text(
            text = "$uid\n$password\n$phone",
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TextDemoTheme {
        Main()
    }
}