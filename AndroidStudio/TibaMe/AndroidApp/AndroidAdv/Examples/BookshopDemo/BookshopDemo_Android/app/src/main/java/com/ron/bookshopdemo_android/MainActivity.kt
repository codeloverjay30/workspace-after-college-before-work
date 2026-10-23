package com.ron.bookshopdemo_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.ron.bookshopdemo_android.ui.theme.BookshopDemo_AndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookshopDemo_AndroidTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    var isLoggedIn by remember { mutableStateOf(false) }
    var showRegister by remember { mutableStateOf(false) }

    if (isLoggedIn) {
        // 登入成功後進入書的展示流程
        BookMainScreen()
    } else {
        if (showRegister) {
            // 顯示註冊頁面
            RegisterScreen(
                // 註冊成功等於登入成功
                onRegisterSuccess = { isLoggedIn = true },
                // 返回登入頁面
                onBackToLogin = { showRegister = false })
        } else {
            // 顯示登入頁面
            LoginScreen(
                onLoginSuccess = { isLoggedIn = true },
                // 切換到註冊頁面
                onRegisterClick = { showRegister = true })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BookshopDemo_AndroidTheme {
        Main()
    }
}