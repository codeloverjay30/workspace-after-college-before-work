package com.ron.icondemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ron.icondemo.ui.theme.IconDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IconDemoTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_home_24),
            // 設定文字描述主要用於無障礙服務
            contentDescription = "home",
            // Icon有tint屬性可設定顏色
            tint = Color.DarkGray
        )
        Icon(
            imageVector = Icons.Default.Email,
            contentDescription = "email",
            tint = Color.Blue
        )
        Icon(
            painter = painterResource(id = R.drawable.cat),
            contentDescription = "cat"
            // 未設定tint，預設為黑色
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    IconDemoTheme {
        Main()
    }
}