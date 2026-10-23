package com.jay30.annotationdemo6.enumclass

import androidx.annotation.ColorRes
import com.jay30.annotationdemo6.R

enum class MainColorEnum(
    @ColorRes id : Int,
) {
    MAIN_FRAME_COLOR(id = R.color.purple_200)
}