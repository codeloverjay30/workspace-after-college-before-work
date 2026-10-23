package com.jay30.customizeannotationdemo1.javasqltime.util

import com.jay30.customizeannotationdemo1.javasqltime.vo.JavaSqlTimeFormat

object JavaSqlTimeConverter{
    fun valueOf(/* `AnnotationTarget.VALUE_PARAMETER` must be in array of argument `allowedTargets`. */@JavaSqlTimeFormat formatString: String):java.sql.Time{
        return java.sql.Time.valueOf(formatString)
    }
}