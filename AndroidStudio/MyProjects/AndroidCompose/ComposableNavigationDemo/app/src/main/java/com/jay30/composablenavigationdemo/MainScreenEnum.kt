package com.jay30.composablenavigationdemo

import androidx.annotation.StringRes

enum class MainScreenEnum (@StringRes val title:Int){
    MAIN_FRAME(title = R.string.main_frame),
    SEXY_STORE(title = R.string.sexy_store),
    PROFILE_FRAME(title = R.string.profile_frame),
}