package com.jay30.customizeannotationdemo1.javasqltime.factory

import com.jay30.customizeannotationdemo1.javasqltime.vo.JavaSqlTimeFormat

class JavaSqlTimeFormatFactory2(
    // `AnnotationTarget.VALUE_PARAMETER` and `AnnotationTarget.PROPERTY` must be in array of argument `allowedTargets`.
    @JavaSqlTimeFormat val formatString: String
) {
    fun create(): JavaSqlTimeFormat {
        return JavaSqlTimeFormat(formatString)
    }
}