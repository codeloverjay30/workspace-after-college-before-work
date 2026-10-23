package com.example.exer4_02.ui.components.slider.slider

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

object MySliderTest {
    @Composable
    fun test1(){
        val sliderPosition = remember {  mutableIntStateOf(0) }
        Column {
            val mySlider = MySlider(
                sliderPosition,
                Color.Blue,
                Color.Blue,
                Color.White,
                30u,
                1f..30f
            )

            Spacer(modifier = Modifier.height(20.dp).fillMaxWidth())

            mySlider.popUp()

            Spacer(modifier = Modifier.height(20.dp).fillMaxWidth())

            Text(sliderPosition.value.toString())

        }
    }
}