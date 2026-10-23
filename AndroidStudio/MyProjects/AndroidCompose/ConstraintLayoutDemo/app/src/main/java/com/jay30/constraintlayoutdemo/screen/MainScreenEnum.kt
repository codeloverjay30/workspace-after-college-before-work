package com.jay30.constraintlayoutdemo.screen

import androidx.annotation.StringRes
import com.jay30.constraintlayoutdemo.R

enum class MainScreenEnum (@StringRes val title:Int){
    CONSTRAINT_LAYOUT_DEMO_FRAME(title = R.string.constraint_layout_demo_frame),
    DECOUPLED_CONSTRAINT_LAYOUT_DEMO_FRAME(title = R.string.decoupled_constraint_layout_demo_frame),
    MAIN_FRAME(title = R.string.main_frame),
    CONSTRAINT_LAYOUT_GUIDELINES_DEMO_FRAME(title = R.string.constraint_layout_guidelines_demo_frame),
}