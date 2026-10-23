package com.tibame.tip101.group02.finalproject.healthhelper.enumclass

import androidx.annotation.StringRes
import com.tibame.tip101.group02.finalproject.healthhelper.R

enum class ScreenEnum(
    @StringRes val title: Int,
){
    DietDiaryMainFrame(title = R.string.diet_diary_main_frame),
    AddNewDietDiaryItemFrame(title = R.string.add_new_diet_diary_item_frame),
    SearchHintFrame(title = R.string.search_hint_frame),
    SharableAppFrame(title = R.string.sharable_app_frame),
    DrawPieChartFrame(title = R.string.draw_piechart_frame),
}