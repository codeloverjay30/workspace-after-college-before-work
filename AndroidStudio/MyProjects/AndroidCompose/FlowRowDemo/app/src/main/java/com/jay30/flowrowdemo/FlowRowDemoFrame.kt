package com.jay30.flowrowdemo

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import android.util.Log
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FlowRowDemoFrame(){
        FlowRow(modifier = Modifier.padding(8.dp)) {
            AssistChip(
                onClick = { Log.d("Assist chip", "hello world") },
                label = { Text("Assist chip") },
                leadingIcon = {
                    Icon(
                        Icons.Filled.Settings,
                        contentDescription = "Localized description",
                        Modifier.size(AssistChipDefaults.IconSize)
                    )
                }
            )
        }
}