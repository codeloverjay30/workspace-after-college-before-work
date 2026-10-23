package com.jay30.servlettestproject1

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.BufferedInputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

const val TAG = "MainScreen"

val INPUT_SIZE = 1024

val url = URL("http://www.android.com/")
var readBytes: ByteArray = ByteArray(INPUT_SIZE)
var retValue: Int = -1
var readText: String = ""

@Composable
fun MainScreen() {
    /*
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Text(readText.toString())
    }

     */
}

suspend fun readingData() {
    coroutineScope {
        launch {
            Log.d(TAG, "url:${url}")
            val urlConnection = url.openConnection() as HttpURLConnection
            try {
                Log.d(TAG, "urlConnection:${urlConnection}")
                Log.d(TAG, "urlConnection.url:${urlConnection.url}")
                Log.d(TAG, "urlConnection.responseCode:${urlConnection.responseCode}")
                Log.d(TAG, "urlConnection.requestMethod:${urlConnection.requestMethod}")
                Log.d(TAG, "urlConnection.responseMessage:${urlConnection.responseMessage}")
                Log.d(TAG, "urlConnection.readTimeout:${urlConnection.readTimeout}")
                Log.d(TAG, "urlConnection is connected.")
                /*
            val inputStream: InputStream = BufferedInputStream(urlConnection.inputStream)
            Log.d(TAG, "inputStream:${inputStream}")
            do {
                retValue = inputStream.read(readBytes)
                readText += readBytes.toString()
            } while (retValue != -1)
            inputStream.close()
            Log.d(TAG, "inputStream is closed.")
            urlConnection.disconnect()
            Log.d(TAG, "urlConnection is disconnected.")

             */
            } catch (ex: Exception) {
                Log.d(TAG, "-".repeat(50))
                Log.d(TAG, ex.message.toString())
                Log.d(TAG, "ex.stackTrace:")
                ex.stackTrace.forEachIndexed { index, stackTraceElement ->
                    Log.d(TAG, "index:${index},stackTraceElement:${stackTraceElement}")
                }
                Log.d(TAG, "-".repeat(50))

            }
        }
    }
}