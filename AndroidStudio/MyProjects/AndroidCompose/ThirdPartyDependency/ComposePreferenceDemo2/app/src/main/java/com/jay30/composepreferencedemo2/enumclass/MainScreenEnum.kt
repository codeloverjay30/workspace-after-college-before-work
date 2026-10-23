package com.jay30.composepreferencedemo2.enumclass

import androidx.annotation.StringRes
import com.jay30.composepreferencedemo2.R

enum class MainScreenEnum(
    @StringRes val titleResId:Int,
) {
    MAIN_FRAME(R.string.main_frame_topappbar_title),
    CONNECTION_SETTING_FRAME(R.string.connections_topappbar_title),
    SOUNDS_AND_VIBRATION_SETTING_FRAME(R.string.sounds_and_vibration_topappbar_title),
    CALL_VIBRATION_SETTING_FRAME(R.string.call_vibration_topappbar_title),
    VOLUME_SETTING_FRAME(R.string.volume_topappbar_title),

}