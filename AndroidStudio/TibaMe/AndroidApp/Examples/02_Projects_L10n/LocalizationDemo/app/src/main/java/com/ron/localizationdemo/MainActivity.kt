package com.ron.localizationdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ron.localizationdemo.ui.theme.LocalizationDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LocalizationDemoTheme {
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
            .padding(16.dp)
            // 如果內容過多，會有捲動功能，模擬器有效，預覽無效
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Hello ${stringResource(id = R.string.android)}!",
        )
    }
}

@Preview(name = "en", locale = "en", showBackground = true)
@Preview(name = "zh-rTW", locale = "zh-rTW", showBackground = true)
@Composable
fun DefaultPreview() {
    LocalizationDemoTheme {
        Main()
    }
}