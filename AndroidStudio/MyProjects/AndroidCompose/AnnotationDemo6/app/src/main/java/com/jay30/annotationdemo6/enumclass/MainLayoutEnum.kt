package com.jay30.annotationdemo6.enumclass

import androidx.annotation.LayoutRes
import com.jay30.annotationdemo6.R

enum class MainLayoutEnum(
    @LayoutRes id:Int,
) {
    MAIN_FRAME_LAYOUT(R.layout.main_frame_layout)
}