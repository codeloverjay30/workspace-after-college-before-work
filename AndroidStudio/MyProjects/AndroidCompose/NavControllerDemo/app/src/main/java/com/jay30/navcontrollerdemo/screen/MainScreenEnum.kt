package com.jay30.navcontrollerdemo.screen

import androidx.annotation.StringRes
import com.jay30.navcontrollerdemo.R

enum class MainScreenEnum (@StringRes val title:Int){
    WTF_FRAME(title = R.string.wtf_frame),
    WHAT_FRAME(title = R.string.what_frame),
}