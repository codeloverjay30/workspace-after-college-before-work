package com.tibame.tip101.group02.finalproject.healthhelper.viewmodel

import com.tibame.tip101.group02.finalproject.healthhelper.dataclasses.Diary

object DiaryViewModel {
    val diaries = mutableListOf<Diary>()

    fun addDiary(
        name:String,
        foodName:String,
        date:String,
        time:String,
    ){
        val diary = Diary(
            name = name,
            foodName = foodName ,
            date = date,
            time = time,
        )
        diaries.add(diary)
    }
}