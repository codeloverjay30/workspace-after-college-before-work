package com.jay30.customizeannotationdemo1

import com.jay30.customizeannotationdemo1.javasqltime.vo.JavaSqlTimeFormat

// `AnnotationTarget.PROPERTY` must be in array of argument `allowedTargets`.
// instead of `AnnotationTarget.LOCAL_VARIABLE`
// What a surprise fact.
@JavaSqlTimeFormat val commonJavaSqlTimeFormat = "hh:mm:ss"