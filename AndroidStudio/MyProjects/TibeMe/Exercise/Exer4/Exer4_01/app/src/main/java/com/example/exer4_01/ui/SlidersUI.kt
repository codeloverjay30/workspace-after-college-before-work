package com.example.exer4_01.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Slider

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.example.exer4_01.util.components.slider.MySlider

object SlidersUI {
    @Composable
    fun popUp(){
        val blackSlider = MySlider(
            initSliderPosition = 0f,
            thumbColor = Color.Black,
            activeTrackColor = Color.Black,
            inactiveTrackColor = Color.White,
            steps = 256u,
            valueRange = 0f..255f
        )

        val redSlider = MySlider(
            initSliderPosition = 0f,
            thumbColor = Color.Red,
            activeTrackColor = Color.Red,
            inactiveTrackColor = Color.White,
            steps = 256u,
            valueRange = 0f..255f
        )

        val greenSlider = MySlider(
            initSliderPosition = 0f,
            thumbColor = Color.Green,
            activeTrackColor = Color.Green,
            inactiveTrackColor = Color.White,
            steps = 256u,
            valueRange = 0f..255f
        )
        val blueSlider = MySlider(
            initSliderPosition = 0f,
            thumbColor = Color.Blue,
            activeTrackColor = Color.Blue,
            inactiveTrackColor = Color.White,
            steps = 256u,
            valueRange = 0f..255f
        )

        Column {
            blackSlider.popUp()
            redSlider.popUp()
            greenSlider.popUp()
            blueSlider.popUp()
        }
    }
}