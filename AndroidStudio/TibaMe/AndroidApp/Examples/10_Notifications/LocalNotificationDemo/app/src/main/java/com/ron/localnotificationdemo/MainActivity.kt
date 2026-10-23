package com.ron.localnotificationdemo

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.ron.localnotificationdemo.ui.theme.LocalNotificationDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 檢查是否由通知訊息開啟activity，並進一步取得由該訊息轉來的資料
        val data = intent?.getStringExtra("data") ?: ""

        setContent {
            LocalNotificationDemoTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    if (data.isEmpty()) {
                        NotificationWithIntentExample()
                    } else {
                        // 如果有訊息資料即開啟新的composable呈現
                        NotificationContent(data)
                    }
                }
            }
        }
    }
}

@SuppressLint("MissingPermission")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun NotificationWithIntentExample() {
    val context = LocalContext.current
    val notificationId = 1
    val notificationChannelId = "my_channel_id"

    // API 33開始發通知訊息需要POST_NOTIFICATIONS授權
    val locationPermission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        rememberPermissionState(
            Manifest.permission.POST_NOTIFICATIONS
        )
    } else {
        null
    }

    LaunchedEffect(Unit) {
        if (locationPermission != null) {
            if (!locationPermission.status.isGranted) {
                locationPermission.launchPermissionRequest()
            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(modifier = Modifier.fillMaxWidth(0.7f),
            onClick = {
                createNotificationChannel(context, notificationChannelId)

                // 建立intent是為了使用者點擊通知訊息時可開啟指定的activity，並攜帶資料過去
                val intent = Intent(context, MainActivity::class.java).apply {
                    putExtra("data", "We have something new for you!!")
                }
                // FLAG_IMMUTABLE代表建立的PendingIntent會持續存在，FLAG_UPDATE_CURRENT代表可以修改內容
                val pendingIntent: PendingIntent = PendingIntent.getActivity(
                    context, 0, intent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )

                // 建立通知訊息
                val notification = NotificationCompat.Builder(context, notificationChannelId)
                    .setSmallIcon(android.R.drawable.ic_dialog_info)
                    .setContentTitle("Local Notification")
                    .setContentText("Tap to open and see the message.")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    // 設定intent是為了開啟activity
                    .setContentIntent(pendingIntent)
                    // 當使用者點擊通知訊息後會自動移除該訊息
                    .setAutoCancel(true)
                    .build()

                // 得到授權就發送通知訊息
                if (locationPermission != null) {
                    if (locationPermission.status.isGranted) {
                        NotificationManagerCompat.from(context).notify(notificationId, notification)
                    } else {
                        Toast.makeText(context, "Post Notification not granted", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }) {
            Text("Send Notification")
        }
        Button(modifier = Modifier.fillMaxWidth(0.7f),
            onClick = { NotificationManagerCompat.from(context).cancel(notificationId) }) {
            Text("Cancel Notification")
        }
    }
}

fun createNotificationChannel(context: Context, notificationChannelId: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = "my_channel_name"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(notificationChannelId, name, importance)
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}

@Composable
fun NotificationContent(content: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Text(text = content, style = MaterialTheme.typography.titleLarge)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LocalNotificationDemoTheme {
        NotificationWithIntentExample()
    }
}