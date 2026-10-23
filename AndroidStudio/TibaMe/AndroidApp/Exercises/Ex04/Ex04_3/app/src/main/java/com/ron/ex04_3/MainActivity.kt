package com.ron.ex04_3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ron.ex04_3.ui.theme.Ex04_3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Ex04_3Theme {
                Main()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main() {
    val options = listOf("春", "夏", "秋", "冬")
    // 被選取項目的文字
    var selectedText by remember { mutableStateOf("") }
    // 結果文字
    var resultText by remember { mutableStateOf("") }
    // 依照輸入過濾選項開頭文字(不區別大小寫)
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        ExposedDropdownMenuBox(
            // 是否彈出下拉選單
            expanded = expanded,
            // 點擊下拉選單且展開狀態改變時呼叫，會傳入目前是否為展開狀態
            onExpandedChange = { expanded = it }
        ) {
            // 使用TextField以外元件會造成無法正常顯示下拉選單
            TextField(
                // 設為true則無法輸入
                readOnly = true,
                // 正確設定TextField與ExposedDropdownMenu對應位置。enabled為true方可展開下拉選單
                modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryEditable, true),
                value = selectedText,
                // 當使用者修改文字值時呼叫
                onValueChange = {
                    selectedText = it
                    // 值改變時會彈出選項
                    expanded = true
                },
                singleLine = true,
                label = { Text("選擇季節") },
                // 末端顯示下箭頭圖示
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            )
            ExposedDropdownMenu(
                // 設定是否彈出下拉選單
                expanded = expanded,
                // 點擊下拉選單外部時
                onDismissRequest = { expanded = false }
            ) {
                // 下拉選單內容由DropdownMenuItem選項元件組成
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        // 點選項目後呼叫
                        onClick = {
                            selectedText = option
                            // 將狀態設定為收回下拉選單
                            expanded = false
                            // 準備欲顯示的結果文字
                            when (option) {
                                "春" -> resultText = "春暖花開"
                                "夏" -> resultText = "夏日炎炎"
                                "秋" -> resultText = "秋高氣爽"
                                "冬" -> resultText = "冬風凜冽"
                            }
                        }
                    )
                }
            }
        }
        // 顯示選取項目文字，而非使用者輸入的搜尋文字
        Text(resultText)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Ex04_3Theme {
        Main()
    }
}