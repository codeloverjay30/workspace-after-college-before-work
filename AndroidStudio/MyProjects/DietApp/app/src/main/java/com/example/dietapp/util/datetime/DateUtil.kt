package com.example.dietapp.util.datetime

import com.example.dietapp.util.dateformatter.DateFormatterPattern
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * DateUtil
 *
 * util object for date
 */
object DateUtil {
    fun isValidDate(dateStr:String):Boolean{
        try {
            val formatter = SimpleDateFormat(DateFormatterPattern.pattern, Locale.getDefault())
            val date = formatter.parse(dateStr)
            return true
        }catch (ex:Exception){
            return false
        }
    }
}