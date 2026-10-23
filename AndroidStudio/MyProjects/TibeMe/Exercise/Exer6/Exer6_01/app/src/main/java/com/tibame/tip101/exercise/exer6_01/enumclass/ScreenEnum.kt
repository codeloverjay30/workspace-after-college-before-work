package com.tibame.tip101.exercise.exer6_01.enumclass

import androidx.annotation.StringRes
import com.tibame.tip101.exercise.exer6_01.R

enum class ScreenEnum(
    @StringRes val title: Int,
){
    BMI_SCREEN(title = R.string.bmi_screen),
    BMI_RESULT_SCREEN(title = R.string.bmi_result_screen),
}