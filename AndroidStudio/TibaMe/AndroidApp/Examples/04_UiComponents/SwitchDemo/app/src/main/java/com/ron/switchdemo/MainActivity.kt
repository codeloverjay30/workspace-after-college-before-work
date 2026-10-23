package com.ron.switchdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ron.switchdemo.ui.theme.SwitchDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SwitchDemoTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    var swTaiwan by remember { mutableStateOf(false) }
    var swJapan by remember { mutableStateOf(false) }
    var swUk by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SwitchWithText(text = "Taiwan", swTaiwan) {
            swTaiwan = it
        }
        SwitchWithText(text = "Japan", swJapan) {
            swJapan = it
        }
        SwitchWithText(text = "UK", swUk) {
            swUk = it
        }
        val result = if (swTaiwan) {
            "Taiwan"
        } else {
            ""
        } + " " + if (swJapan) {
            "Japan"
        } else {
            ""
        } + " " + if (swUk) {
            "UK"
        } else {
            ""
        }
        Text(text = result, modifier = Modifier.padding(top = 32.dp))
    }
}

@Composable
fun SwitchWithText(
    text: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = text, modifier = Modifier.width(100.dp))
        // 建立switch
        Switch(
            // 開關狀態
            checked = checked,
            // 開關狀態改變時呼叫
            onCheckedChange = onCheckedChange,
            // 設定圖示：選取時顯示勾選圖示，取消選取時則不顯示圖示
            thumbContent =
            if (checked) {
                {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "Check",
                        modifier = Modifier.size(SwitchDefaults.IconSize),
                    )
                }
            } else {
                null
            },
            // 設定顏色
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = Color.Green,
                uncheckedThumbColor = Color.DarkGray,
                uncheckedTrackColor = Color.LightGray
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SwitchDemoTheme {
        Main()
    }
}