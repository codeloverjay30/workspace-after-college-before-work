package com.jay30.annotationdemo6.enumclass

import androidx.annotation.StyleRes
import com.jay30.annotationdemo6.R

enum class MainStyleEnum(
    @StyleRes id:Int,
){
    MAIN_FRAME_STYLE(id = R.style.main_frame_theme)
}