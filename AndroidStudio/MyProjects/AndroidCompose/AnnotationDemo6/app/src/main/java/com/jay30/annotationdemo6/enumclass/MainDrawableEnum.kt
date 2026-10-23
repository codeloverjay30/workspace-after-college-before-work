package com.jay30.annotationdemo6.enumclass

import androidx.annotation.DrawableRes
import com.jay30.annotationdemo6.R

enum class MainDrawableEnum(
    @DrawableRes id:Int,
) {
    MAIN_FRAME_ICON(R.drawable.ic_launcher_background)
}