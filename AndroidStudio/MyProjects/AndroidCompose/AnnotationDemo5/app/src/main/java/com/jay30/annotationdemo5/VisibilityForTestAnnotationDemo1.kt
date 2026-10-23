package com.jay30.annotationdemo5

import androidx.annotation.VisibleForTesting

@VisibleForTesting(otherwise = VisibleForTesting.NONE)
fun VisibilityForTestingDemo1(){

}

@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
fun VisibilityForTestingDemo2(){

}

// Equivalent to `@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)`
@VisibleForTesting
fun VisibilityForTestingDemo3(){

}

@VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
fun VisibilityForTestingDemo4(){

}

@VisibleForTesting(otherwise = VisibleForTesting.PACKAGE_PRIVATE)
fun VisibilityForTestingDemo5(){

}