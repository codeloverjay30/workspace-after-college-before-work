package com.ron.fcmdemo_android

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainVM : ViewModel() {
    private val myTag = "tag_MainVM"
    private val url = "${serverUrl}/FcmBasicServlet"
    private val topic = "health"

    // 來自於repository儲存的資料
    private val repository = MyRepository
    val messageState: StateFlow<String> = repository.messageState

    /** 取得token並送到server */
    fun getTokenSendServer() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                task.result?.let { token ->
                    // 複製token到Firebase的Cloud Messaging測試區
                    Log.d(myTag, "token: $token")
                    sendTokenToServer(token)
                }
            }
        }
    }

    /** 將token送到server */
    private fun sendTokenToServer(token: String) {
        val jsonObject = JsonObject()
        jsonObject.addProperty("action", "register")
        jsonObject.addProperty("registrationToken", token)
        viewModelScope.launch {
            httpPost(url, jsonObject.toString())
        }
    }

    /** 發送單一FCM */
    suspend fun sendSingleFcm(title: String, body: String, data: String): String {
        val jsonObject = JsonObject()
        jsonObject.addProperty("action", "singleFcm")
        jsonObject.addProperty("title", title)
        jsonObject.addProperty("body", body)
        jsonObject.addProperty("data", data)
        return httpPost(url, jsonObject.toString())
    }

    /** 發送群組FCM */
    suspend fun sendGroupFcm(title: String, body: String, data: String): String {
        val jsonObject = JsonObject()
        jsonObject.addProperty("action", "groupFcm")
        jsonObject.addProperty("title", title)
        jsonObject.addProperty("body", body)
        jsonObject.addProperty("data", data)
        return httpPost(url, jsonObject.toString())
    }

    /** 訂閱指定主題 */
    fun subscribe() {
        FirebaseMessaging.getInstance().subscribeToTopic(topic)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(myTag, "完成訂閱")
                }
            }
    }

    /** 發FCM給指定主題的訂閱者 */
    suspend fun sendSubscriber(): String {
        val jsonObject = JsonObject()
        jsonObject.addProperty("action", "subscriberFcm")
        jsonObject.addProperty("topic", topic)
        return httpPost(url, jsonObject.toString())
    }

}