package com.example.dietapp.util.datetime

import java.text.SimpleDateFormat
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
}