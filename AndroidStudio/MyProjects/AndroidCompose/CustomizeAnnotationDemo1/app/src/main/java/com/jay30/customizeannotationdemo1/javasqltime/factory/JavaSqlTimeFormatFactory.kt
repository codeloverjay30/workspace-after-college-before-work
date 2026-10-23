package com.jay30.customizeannotationdemo1.javasqltime.factory

import com.jay30.customizeannotationdemo1.javasqltime.vo.JavaSqlTimeFormat

class JavaSqlTimeFormatFactory {
    companion object{
        fun create(
            // `AnnotationTarget.VALUE_PARAMETER` must be in array of argument `allowedTargets`.
            @JavaSqlTimeFormat formatString: String
        ): JavaSqlTimeFormat {
            return JavaSqlTimeFormat(formatString)
        }
    }
}