package com.ron.fcmdemo_android

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.ron.fcmdemo_android.ui.theme.FCMDemo_AndroidTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 檢查是否由通知訊息開啟activity，並進一步取得由該訊息轉來的資料存入repository
        val data = intent?.getStringExtra("data") ?: ""
        val repository = MyRepository
        repository.setMessage(data)

        setContent {
            FCMDemo_AndroidTheme {
                Main()
            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Main(mainVM: MainVM = viewModel()) {
    val context = LocalContext.current
    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }
    var data by remember { mutableStateOf("") }
    var text by remember { mutableStateOf("") }
    val message by mainVM.messageState.collectAsState()
    val coroutineScope = rememberCoroutineScope()

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
            .padding(top = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(0.7f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text(text = stringResource(id = R.string.title)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = body,
                onValueChange = { body = it },
                label = { Text(text = stringResource(id = R.string.body)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = data,
                onValueChange = { data = it },
                label = { Text(text = stringResource(id = R.string.data)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    coroutineScope.launch {
                        text = mainVM.sendSingleFcm(title, body, data)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.send_single_fcm))
            }

            Button(
                onClick = {
                    coroutineScope.launch {
                        text = mainVM.sendGroupFcm(title, body, data)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.send_group_fcm))
            }

            Button(
                onClick = {
                    mainVM.subscribe()
                    text = context.getString(R.string.subscribed)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.subscribe_health))
            }

            Button(
                onClick = {
                    coroutineScope.launch {
                        text = mainVM.sendSubscriber()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.send_subscriber))
            }
            Button(
                onClick = {
                    coroutineScope.launch {
                        mainVM.getTokenSendServer()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.get_token))
            }

            Button(
                onClick = {
                    title = ""
                    body = ""
                    data = ""
                    text = ""
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.clear))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = text)
        Text(text = message)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FCMDemo_AndroidTheme {
        Main()
    }
}