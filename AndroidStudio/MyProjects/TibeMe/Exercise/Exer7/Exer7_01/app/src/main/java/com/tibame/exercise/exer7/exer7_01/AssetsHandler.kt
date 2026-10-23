package com.tibame.exercise.exer7.exer7_01

import android.content.Context

object AssetsHandler {
    /**
     * 讀取assets目錄內指定檔案
     */
    fun readAssetFile(context: Context, fileName: String): String {
        val inputStream = context.assets.open(fileName)
        inputStream.bufferedReader().useLines { lines ->
            val result = lines.fold("") { accum, line ->
                "$accum$line\n"
            }
            return result
        }
    }
}