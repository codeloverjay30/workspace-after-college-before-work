package com.ron.fcmtestdemo

import android.Manifest
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.ron.fcmtestdemo.ui.theme.FCMTestDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 檢查是否由通知訊息開啟activity，並進一步取得由該訊息轉來的資料存入repository
        val data = intent?.getStringExtra("data") ?: ""
        val repository = MyRepository
        repository.setData(data)

        setContent {
            FCMTestDemoTheme {
                NotificationWithIntentExample()
            }
        }
    }
}

@SuppressLint("MissingPermission")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
// 加上androidx.lifecycle:lifecycle-viewmodel-compose-android函式庫
fun NotificationWithIntentExample(mainVM: MainVM = viewModel()) {
    val token by mainVM.tokenFlow.collectAsState()
    val data by mainVM.dataFlow.collectAsState()

    // API 33開始發通知訊息需要POST_NOTIFICATIONS授權
    // 加上com.google.accompanist:accompanist-permissions函式庫
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
            .padding(16.dp)
    ) {
        // token顯示在TextField方便複製
        TextField(value = token, onValueChange = {})
        Spacer(modifier = Modifier.height(16.dp))
        // 無論前景或背景收到FCM都會顯示在此
        Text(text = data, style = MaterialTheme.typography.titleLarge)
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FCMTestDemoTheme {
        NotificationWithIntentExample()
    }
}