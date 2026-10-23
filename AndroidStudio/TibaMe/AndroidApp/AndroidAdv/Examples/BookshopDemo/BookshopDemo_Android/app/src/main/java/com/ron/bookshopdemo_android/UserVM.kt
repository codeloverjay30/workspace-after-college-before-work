package com.ron.bookshopdemo_android

import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.JsonObject

class UserVM : ViewModel() {
    // 登入
    suspend fun login(uid: String, password: String): Boolean {
        // server URL
        val url = "${serverUrl}/LoginServlet"
        val gson = Gson()
        var jsonObject = JsonObject()

        // 將帳密轉成JSON
        jsonObject.addProperty("uid", uid)
        jsonObject.addProperty("password", password)

        // 發出POST請求，取得登入結果後回傳
        val result = httpPost(url, jsonObject.toString())
        jsonObject = gson.fromJson(result, jsonObject::class.java)
        return jsonObject.get("logged").asBoolean
    }

    // 註冊
    suspend fun register(uid: String, password: String): Boolean {
        // server URL
        val url = "${serverUrl}/RegisterServlet"
        val gson = Gson()
        var jsonObject = JsonObject()

        // 將帳密轉成JSON
        jsonObject.addProperty("uid", uid)
        jsonObject.addProperty("password", password)

        // 發出POST請求，取得登入結果後回傳
        val result = httpPost(url, jsonObject.toString())
        jsonObject = gson.fromJson(result, jsonObject::class.java)
        return jsonObject.get("registered").asBoolean
    }

}