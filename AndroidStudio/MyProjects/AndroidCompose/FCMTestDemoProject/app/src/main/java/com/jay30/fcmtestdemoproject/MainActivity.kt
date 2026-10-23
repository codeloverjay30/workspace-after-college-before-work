package com.jay30.fcmtestdemoproject

import android.Manifest
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import com.jay30.fcmtestdemoproject.ui.theme.FCMTestDemoProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FCMTestDemoProjectTheme {
                val TAG = "tag_MainActivity"
                val data = intent?.getStringExtra("data") ?: ""
                val repository = MyRepository
                Log.d(TAG,"Data:$data")
                repository.setData(data)
                NotificationWithIntentExample()
            }
        }
    }
}


@OptIn(ExperimentalPermissionsApi::class)
@SuppressLint("MissingPermission")
@Composable
fun NotificationWithIntentExample(mainVM: MainVM = viewModel()) {
    val token by mainVM.tokenFlow.collectAsState()
    val data by mainVM.dataFlow.collectAsState()

    val permission =
        rememberPermissionState(Manifest.permission.POST_NOTIFICATIONS)

    LaunchedEffect(Unit) {
        if (!permission.status.isGranted) {
            permission.launchPermissionRequest()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(value = token, readOnly = false, onValueChange = {})
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = data, style = MaterialTheme.typography.titleLarge)
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FCMTestDemoProjectTheme {
        NotificationWithIntentExample()
    }
}