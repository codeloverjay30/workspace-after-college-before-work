package com.example.exer4_03.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

object SpringUI {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun popUp(){
        val springMap = mapOf(
            "春" to "春暖花開",
            "夏" to "夏日炎炎",
            "秋" to "秋高氣爽",
            "冬" to "冬風凜冽",
        )
        val options = springMap.keys.toList()

        var selectedText by remember { mutableStateOf("") }
        var message by remember { mutableStateOf("") }
        var expanded by remember { mutableStateOf(true) }
        val onValueChange = { v :String -> message = v }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 64.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ExposedDropdownMenuBox(
                // 是否彈出下拉選單
                expanded = expanded,
                // 點擊下拉選單且展開狀態改變時呼叫，會傳入目前是否為展開狀態
                onExpandedChange = { expanded = it }
            ) {
                TextField(
                    value = selectedText,
                    onValueChange = onValueChange,
                    modifier = Modifier.menuAnchor(),
                    readOnly = true,
                    placeholder = {},
                    label = { Text("選擇季節") },
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    options.forEach {
                        DropdownMenuItem(
                            text = { Text(text = it) },
                            onClick = {
                                expanded = false
                                selectedText = it
                                message = springMap.getOrDefault(it, "not found")
                            }
                        )
                    }
                }
            }
            Text(message)
        }
    }
}