package com.tibame.tip101.exercise.exer6_03

import androidx.annotation.StringRes

enum class Screen(
    @StringRes val title: Int,
){
    LoginScreen(title = R.string.login_screen),
    DeviceScreen(title = R.string.device_screen),
}