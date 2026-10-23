package com.example.dietapp

import android.util.Log
import com.example.dietapp.util.datetime.DateUtil
import org.junit.Test

class ExampleUnitTest {
    private final val TAG = "ExampleUnitTest"
    @Test
    fun isValidDateExample1(){
        val date = "01/01/2001"
        assert(DateUtil.isValidDate(date))
        Log.d(TAG,"isValidDateExample1")
    }

    @Test
    fun isValidDateExample2(){
        val date = "10/01/2001"
        assert(DateUtil.isValidDate(date))
        Log.d(TAG,"isValidDateExample2")
    }
}