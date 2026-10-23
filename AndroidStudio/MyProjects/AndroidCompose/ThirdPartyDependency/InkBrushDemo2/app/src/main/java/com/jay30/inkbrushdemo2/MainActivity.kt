package com.jay30.inkbrushdemo2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import android.binding.adapters.Converters
import androidx.ink.brush.Brush
import com.jay30.inkbrushdemo2.ui.theme.InkBrushDemo2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InkBrushDemo2Theme {
                val converters = Converters()
                val currentBrush = rememberSaveable(saver = brushStateSaver(converters)) { mutableStateOf(defaultBrush) }
            }
        }
    }
}

fun brushStateSaver(converters: Converters): Saver<MutableState<Brush>, String> = Saver(
    save = { state ->
        converters.brushToString(state.value)
    },
    restore = { jsonString ->
        val brush = converters.stringToBrush(jsonString)
        mutableStateOf(brush)
    }
)