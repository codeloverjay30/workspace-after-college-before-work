package com.jay30.customizeannotationdemo1

import androidx.compose.runtime.Composable
import com.jay30.customizeannotationdemo1.javasqltime.vo.JavaSqlTimeFormat

@Composable
fun MainFrame(){
    // `AnnotationTarget.LOCAL_VARIABLE` must be in array of argument `allowedTargets`.
    @JavaSqlTimeFormat val javaSqlTimeFormatInstance1 = JavaSqlTimeFormat(formatString = "hh:mm:ss")
}