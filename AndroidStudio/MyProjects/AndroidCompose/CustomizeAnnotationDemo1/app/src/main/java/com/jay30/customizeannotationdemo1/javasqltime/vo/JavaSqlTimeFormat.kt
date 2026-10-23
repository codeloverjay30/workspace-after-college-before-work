package com.jay30.customizeannotationdemo1.javasqltime.vo

@Target(
    allowedTargets = [
        AnnotationTarget.LOCAL_VARIABLE,
        AnnotationTarget.TYPE,
        AnnotationTarget.PROPERTY,
        AnnotationTarget.VALUE_PARAMETER
    ]
)
annotation class JavaSqlTimeFormat(
    val formatString: String = "hh:mm:ss"
)
