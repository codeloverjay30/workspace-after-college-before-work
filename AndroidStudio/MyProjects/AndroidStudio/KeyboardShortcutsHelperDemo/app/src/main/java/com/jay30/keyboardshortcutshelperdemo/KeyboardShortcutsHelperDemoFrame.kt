package com.jay30.keyboardshortcutshelperdemo

import android.app.Activity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun KeyboardShortcutsHelperDemoFrame(){
    val activity = LocalContext.current as Activity
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ){
        item {
            Button(onClick = { activity.requestShowKeyboardShortcuts() }) {
                Text(text = "Show keyboard shortcuts")
            }
        }
    }
}