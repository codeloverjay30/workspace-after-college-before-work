package com.ron.webrequestdemo

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

suspend fun getRemoteText(
    url: String
): String {
    var result = ""
    // 執行緒池會開啟新的執行緒處理IO任務
    withContext(Dispatchers.IO) {
        (URL(url).openConnection() as? HttpURLConnection)?.run {
            if (responseCode == 200) {
                inputStream.bufferedReader().useLines { lines ->
                    result = lines.fold("") { text, line -> "$text$line" }
                    Log.d("tag_Common", "input: $result")
                }
            }
        }
    }
    return result
}