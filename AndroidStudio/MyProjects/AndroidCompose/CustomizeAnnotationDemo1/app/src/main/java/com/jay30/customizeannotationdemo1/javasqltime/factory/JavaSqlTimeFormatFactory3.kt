package com.jay30.customizeannotationdemo1.javasqltime.factory

import com.jay30.customizeannotationdemo1.javasqltime.vo.JavaSqlTimeFormat

class JavaSqlTimeFormatFactory3() {
    // `AnnotationTarget.PROPERTY` must be in array of argument `allowedTargets`.
    @JavaSqlTimeFormat private var _formatString: String = ""
    var formatString: String
        // `AnnotationTarget.PROPERTY` must be in array of argument `allowedTargets`.
        // though you won't look at compiler error at this line
        // when `AnnotationTarget.PROPERTY` not in array of argument `allowedTargets`
        // (the reason why may be the annotation is used in return type.),
        // it is needed.
        get(): @JavaSqlTimeFormat String{
            // WRONG!!! annotation can't be placed after `return` keyword.
            // return @JavaSqlTimeFormat _formatString
            return _formatString
        }
        set(
            /* `AnnotationTarget.VALUE_PARAMETER` must be in array of argument `allowedTargets`. */
            @JavaSqlTimeFormat value
        ){
            _formatString = value
        }

    fun create(
    ): JavaSqlTimeFormat {
        return JavaSqlTimeFormat(formatString)
    }
}