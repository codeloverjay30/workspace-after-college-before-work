package com.jay30.chipdemo

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun ChipDemoFrame() {
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            AssistChip(
                onClick = {
                    Toast.makeText(context, "Assist Chip was clicked.", Toast.LENGTH_LONG).show()
                },
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

        item {
            var selected by remember { mutableStateOf(false) }

            FilterChip(
                onClick = {
                    Toast.makeText(context, "Filter Chip was clicked.", Toast.LENGTH_LONG).show()
                    selected = !selected
                },
                label = {
                    Text("Filter chip")
                },
                selected = selected,
                leadingIcon = if (selected) {
                    {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = "Done icon",
                            modifier = Modifier.size(FilterChipDefaults.IconSize)
                        )
                    }
                } else {
                    null
                },
            )
        }
        item {
            var enabled by remember { mutableStateOf(true) }
            InputChip(
                onClick = {
                    Toast.makeText(context, "Input Chip was clicked.", Toast.LENGTH_LONG).show()
                    enabled = !enabled
                },
                label = { Text("Input Chip") },
                selected = enabled,
                avatar = {
                    Icon(
                        Icons.Filled.Person,
                        contentDescription = "Localized description",
                        Modifier.size(InputChipDefaults.AvatarSize)
                    )
                },
                trailingIcon = {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "Localized description",
                        Modifier.size(InputChipDefaults.AvatarSize)
                    )
                },
            )
        }
        item {
            SuggestionChip(
                onClick = {
                    Toast.makeText(context, "Suggestion Chip was clicked.", Toast.LENGTH_LONG)
                        .show()
                },
                label = { Text("Suggestion chip") }
            )
        }
    }
}