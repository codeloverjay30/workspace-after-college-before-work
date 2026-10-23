package com.jay30.composepreferencedemo1

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.zhanghai.compose.preference.ProvidePreferenceLocals
import me.zhanghai.compose.preference.switchPreference

@Composable
fun MainFrame(){
    ProvidePreferenceLocals {
        // Other composables wrapping the LazyColumn ...
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            switchPreference(
                key = "switch_preference",
                defaultValue = false,
                title = { Text(text = "Switch preference") },
                icon = { Icon(imageVector = Icons.Outlined.Info, contentDescription = null) },
                summary = { Text(text = if (it) "On" else "Off") }
            )
        }
    }
}