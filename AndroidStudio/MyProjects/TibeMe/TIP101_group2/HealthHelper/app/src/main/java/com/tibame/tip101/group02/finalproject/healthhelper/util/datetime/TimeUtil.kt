package com.example.dietapp.util.datetime

import android.annotation.SuppressLint
import com.tibame.tip101.group02.finalproject.healthhelper.util.dateformatter.DateFormatterPattern
import com.tibame.tip101.group02.finalproject.healthhelper.util.timeformatter.TimeFormatterPattern
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * TimeUtil
 *
 * util object for date
 */
object TimeUtil {
    fun isValidTime(dateStr:String):Boolean{
        try {
            val formatter = SimpleDateFormat("hh:MM:ss", Locale.getDefault())
            val time = formatter.parse(dateStr)
            return true
        }catch (ex:Exception){
            return false
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun convertMillisToTime(millis: Long): String {
        val formatter = SimpleDateFormat(TimeFormatterPattern.pattern)
        return formatter.format(Time(millis))
    }
}