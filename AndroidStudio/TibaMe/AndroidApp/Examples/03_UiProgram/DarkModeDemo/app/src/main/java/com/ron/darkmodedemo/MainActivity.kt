package com.ron.darkmodedemo

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ron.darkmodedemo.ui.theme.DarkModeDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DarkModeDemoTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        // 子容器沒有背景色，採用父容器顏色
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            // 子容器沒有背景色，採用父容器顏色
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                /* 父元件Surface的color為colorScheme.surface；
                 非容器子元件未設定顏色，會自動套用colorScheme.onSurface */
                Text(text = "Hello, world!")
            }
            Column(
                // padding()與background()顛倒，背景色範圍不同
                // 子容器自有背景色，優先採用
                modifier = Modifier
                    .background(Color.Gray)
                    .padding(16.dp)
            ) {
                Text(
                    text = "isSystemInDarkTheme(): ${isSystemInDarkTheme()}",
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

// 顯示dark mode需使用uiMode參數
@Preview(name = "dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "light")
@Composable
fun DefaultPreview() {
    DarkModeDemoTheme {
        Main()
    }
}