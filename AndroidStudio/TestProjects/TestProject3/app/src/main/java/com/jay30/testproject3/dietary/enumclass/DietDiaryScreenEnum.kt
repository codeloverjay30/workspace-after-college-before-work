package com.jay30.testproject3.dietary.enumclass

import androidx.annotation.StringRes
import com.jay30.testproject3.R

enum class DietDiaryScreenEnum(
    @StringRes val title: Int,
){
    DietDiaryMainFrame(title = R.string.diet_diary_main_frame),
    DietDiaryMealFrame(title = R.string.diet_diary_meal_frame),
    FoodItemInfoFrame(title = R.string.food_item_info_frame),
}