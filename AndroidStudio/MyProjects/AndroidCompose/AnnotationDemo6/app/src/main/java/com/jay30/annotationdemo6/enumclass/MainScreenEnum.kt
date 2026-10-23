package com.jay30.annotationdemo6.enumclass

import androidx.annotation.StringRes
import com.jay30.annotationdemo6.R

enum class MainScreenEnum(
    @StringRes title:Int,
) {
    MAIN_FRAME(title = R.string.main_frame),
}