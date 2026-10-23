package com.jay30.annotationdemo13

import android.util.Log
import javax.inject.Inject

class Car @Inject constructor(private val engine: Engine) {
    val TAG = "tag_Car"
    fun run() {
        Log.e(TAG,"Car is running with engine: ${engine}")
    }
}
