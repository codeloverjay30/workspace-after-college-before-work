package com.ron.dropdownmenudemo

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
import androidx.compose.material3.ExposedDropdownMenuDefaults.TrailingIcon
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
import com.ron.dropdownmenudemo.ui.theme.DropdownMenuDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DropdownMenuDemoTheme {
                Main()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main() {
    val options = listOf("Large", "Medium", "Small")
    // 使用者輸入的文字
    var inputText by remember { mutableStateOf("") }
    // 被選取項目的文字
    var selectedText by remember { mutableStateOf("") }
    // 依照輸入過濾選項開頭文字(不區別大小寫)
    val filteredOptions = options.filter { it.startsWith(inputText, true) }
    var expanded by remember { mutableStateOf(false) }
    expanded = expanded && filteredOptions.isNotEmpty()

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
                readOnly = false,
                // 正確設定TextField與ExposedDropdownMenu對應位置。enabled為true方可展開下拉選單
                modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryEditable, true),
                value = inputText,
                // 當使用者修改文字值時呼叫
                onValueChange = {
                    inputText = it
                    // 值改變時會彈出選項
                    expanded = true
                },
                singleLine = true,
                label = { Text("T-shirt Size") },
                // 末端顯示下箭頭圖示
                trailingIcon = { TrailingIcon(expanded = expanded) }
            )
            ExposedDropdownMenu(
                // 設定是否彈出下拉選單
                expanded = expanded,
                // 點擊下拉選單外部時
                onDismissRequest = { expanded = false }
            ) {
                // 下拉選單內容由DropdownMenuItem選項元件組成
                filteredOptions.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        // 點選項目後呼叫
                        onClick = {
                            // 將所有文字都改成選取項目文字
                            selectedText = option
                            inputText = option
                            // 將狀態設定為收回下拉選單
                            expanded = false
                        }
                    )
                }
            }
        }
        // 顯示選取項目文字，而非使用者輸入的搜尋文字
        Text(selectedText)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DropdownMenuDemoTheme {
        Main()
    }
}