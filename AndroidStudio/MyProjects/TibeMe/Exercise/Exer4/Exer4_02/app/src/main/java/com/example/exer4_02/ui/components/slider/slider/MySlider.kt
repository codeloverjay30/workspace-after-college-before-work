package com.example.exer4_02.ui.components.slider.slider

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

import androidx.annotation.IntRange
import androidx.compose.runtime.MutableState

class MySlider(
    private var sliderPosition: MutableState<Int>,
    private val thumbColor:Color,
    private val activeTrackColor:Color,
    private val inactiveTrackColor:Color,
    private val steps: UInt = 0u,
    //val steps : @IntRange(from = 0 ) Int = 0,
    private val valueRange: ClosedFloatingPointRange<Float> = 0f..1f
) {
    @Composable
    fun popUp(){
        Row {
            Slider(
                value = sliderPosition.value.toFloat(),
                colors = SliderDefaults.colors(
                    thumbColor = thumbColor,
                    activeTrackColor = activeTrackColor,
                    inactiveTrackColor = inactiveTrackColor,
                ),
                onValueChange = { sliderPosition.value = it.toInt() },
                modifier = Modifier.width(250.dp),
                steps = steps.toInt(),
                valueRange = valueRange
            )
        }
    }
}