package com.ron.fcmdemo_android

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFCMService : FirebaseMessagingService() {
    private val myTag = "tag_MyFCMService"

    // repository採單例模式
    private val repository = MyRepository

    // 當App在前景收到FCM時呼叫，App在背景收到FCM時不會呼叫此方法
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        var title = ""
        var body = ""
        // 取得notification資料，主要為title與body這2個保留字
        remoteMessage.notification?.let { notification ->
            title = notification.title ?: ""
            body = notification.body ?: ""
        }
        // 取得自訂資料
        val data = remoteMessage.data["data"]
        Log.d(myTag, "onMessageReceived():\ntitle: $title, body: $body, data: $data")
        // 前景收到的訊息存入repository
        val message = "title: $title\nbody: $body\ndata: $data"
        repository.setMessage(message)
    }

    // 當registration token更新時呼叫
    override fun onNewToken(token: String) {
        Log.d(myTag, "onNewToken: $token")
//        MainVM().sendTokenToServer(token)
    }

    override fun onDeletedMessages() {
        super.onDeletedMessages()
        Log.d(myTag, "onDeletedMessages")
    }
}