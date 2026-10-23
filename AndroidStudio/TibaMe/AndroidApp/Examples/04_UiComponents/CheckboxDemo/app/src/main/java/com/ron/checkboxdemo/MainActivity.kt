package com.ron.checkboxdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ron.checkboxdemo.ui.theme.CheckboxDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CheckboxDemoTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    var cbTaiwan by remember { mutableStateOf(false) }
    var cbJapan by remember { mutableStateOf(false) }
    var cbUk by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CheckboxWithText(text = "Taiwan", cbTaiwan) {
            cbTaiwan = it
        }
        CheckboxWithText(text = "Japan", cbJapan) {
            cbJapan = it
        }
        CheckboxWithText(text = "UK", cbUk) {
            cbUk = it
        }
        val result = if (cbTaiwan) {
            "Taiwan"
        } else {
            ""
        } + " " + if (cbJapan) {
            "Japan"
        } else {
            ""
        } + " " + if (cbUk) {
            "UK"
        } else {
            ""
        }
        Text(text = result, modifier = Modifier.padding(top = 32.dp))
    }
}

@Composable
fun CheckboxWithText(
    text: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = text, modifier = Modifier.width(100.dp))
        // 建立Checkbox。如果preview無法正確顯示選取狀態，請改用模擬器
        Checkbox(
            // 選取狀態
            checked = checked,
            // 選取狀態改變時呼叫
            onCheckedChange = onCheckedChange,
            // 設定顏色
            colors = CheckboxDefaults.colors(
                checkedColor = colorResource(id = R.color.purple_200),
                uncheckedColor = colorResource(id = R.color.teal_200),
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CheckboxDemoTheme {
        Main()
    }
}