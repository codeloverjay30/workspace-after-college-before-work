package com.tibame.exercise.exer7.exer7_01

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tibame.exercise.exer7.exer7_01.ui.theme.Ex7_01Theme

fun parseFontColor(index: Int): Color {
    return when (index) {
        0 -> Color.Black
        1 -> Color.DarkGray
        2 -> Color.Red
        3 -> Color.Blue
        else -> throw IllegalArgumentException("Unsupported color name: $index")
    }
}
fun parseFontSize(index: Int): Int {
    return when (index) {
        0 -> 12
        1 -> 14
        2 -> 16
        3 -> 18
        else -> throw IllegalArgumentException("Unsupported color name: $index")
    }
}

@Composable
fun PreferencesScreen() {
    val context = LocalContext.current
    val preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
    var colorIndex by remember { mutableIntStateOf(0) }
    var sizeIndex by remember { mutableIntStateOf(0) }
    val colors = stringArrayResource(id = R.array.colors)
    val sizes = stringArrayResource(id = R.array.sizes)

    var colorsExpanded by remember { mutableStateOf(false) }
    var sizesExpanded by remember { mutableStateOf(false) }

    val tag = "tag_PreferencesScreen"
    LaunchedEffect(Unit) {
        Log.d(tag, "LaunchedEffect")
        colorIndex = preferences.getInt("colorIndex", 0)
        sizeIndex = preferences.getInt("sizeIndex", 0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Text Color: ${colors[colorIndex]}")
            Button(
                modifier = Modifier.fillMaxWidth(0.4f),
                onClick = { colorsExpanded = true },
                colors = ButtonDefaults.buttonColors(contentColor = Color.DarkGray, containerColor = Color.Green)
            ) {
                Text(text = colors[colorIndex])
            }
            DropdownMenu(
                expanded = colorsExpanded,
                onDismissRequest = { colorsExpanded = false }) {
                colors.forEachIndexed { index, option ->
                    DropdownMenuItem(
                        text = { Text(text = option) },
                        onClick = {
                            colorsExpanded = false
                            colorIndex = index
                            preferences.edit().putInt("colorIndex", colorIndex).apply()
                        })
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Column {
            Text(text = "Text Size: ${sizes[sizeIndex]}")
            Button(
                modifier = Modifier.fillMaxWidth(0.4f),
                onClick = { sizesExpanded = true },
                colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
            ) {
                Text(text = sizes[sizeIndex])
            }
            DropdownMenu(
                expanded = sizesExpanded,
                onDismissRequest = { sizesExpanded = false }) {
                sizes.forEachIndexed { index, option ->
                    DropdownMenuItem(
                        text = { Text(text = option) },
                        onClick = {
                            sizesExpanded = false
                            sizeIndex = index
                            preferences.edit().putInt("sizeIndex", sizeIndex).apply()
                        })
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreferencesScreenPreview() {
    Ex7_01Theme {
        PreferencesScreen()
    }
}