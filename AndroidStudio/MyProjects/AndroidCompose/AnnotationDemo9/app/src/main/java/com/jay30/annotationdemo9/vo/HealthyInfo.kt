package com.jay30.annotationdemo9.vo

// Default value is AnnotationRetention.RUNTIME
@Retention(value = AnnotationRetention.RUNTIME)
annotation class HealthyInfo(
    val height:Double,
    val weight:Double
)
