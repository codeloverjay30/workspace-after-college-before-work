package com.jay30.composepreferencedemo3.enumclass

import androidx.annotation.StringRes
import com.jay30.composepreferencedemo3.R

enum class MainScreenEnum(
    @StringRes val titleResId:Int,
) {
    MAIN_FRAME(R.string.main_frame_topappbar_title),
    CONNECTION_SETTING_FRAME(R.string.connections_topappbar_title),
    SIM_SETTING_FRAME(R.string.sim_topappbar_title),
    SOUNDS_AND_VIBRATION_SETTING_FRAME(R.string.sounds_and_vibration_topappbar_title),
    CALL_VIBRATION_SETTING_FRAME(R.string.call_vibration_topappbar_title),
    RINGTONE_SETTING_FRAME(R.string.ringtone_topappbar_title),
    VOLUME_SETTING_FRAME(R.string.volume_topappbar_title),
    APP_SETTING_FRAME(R.string.app_topappbar_title),
    ACCOUNT_LIST_SETTING_FRAME(R.string.account_list_topappbar_title),
    ACCOUNT_SETTING_FRAME(R.string.account_topappbar_title),
}