package com.example.dietapp.util.validator

import com.example.dietapp.extension.string.isValidString
import com.example.dietapp.util.datetime.DateUtil
import com.example.dietapp.util.datetime.TimeUtil

object Validator {
     fun isValid(date:String,time:String,vararg strings:String): Boolean{
        if(!DateUtil.isValidDate(date)){
            return false
        }
        if(!TimeUtil.isValidTime(time)){
            return false
        }

        for(string in strings){
            if(!string.isValidString()){
                return false
            }
        }
        return true
    }
}