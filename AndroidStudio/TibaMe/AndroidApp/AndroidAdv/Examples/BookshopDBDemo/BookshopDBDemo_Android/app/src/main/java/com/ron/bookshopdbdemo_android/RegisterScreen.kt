package com.ron.bookshopdbdemo_android

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.ron.bookshopdbdemo_android.ui.theme.BookshopDemo_AndroidTheme
import kotlinx.coroutines.launch
import java.io.InputStream


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RegisterScreen(
    userVM: UserVM = viewModel(),
    onRegisterSuccess: () -> Unit,
    onBackToLogin: () -> Unit,
    onTakePictureClick: () -> Unit,
    onImagePicked: (Uri?) -> Unit,
    imageUri: Uri?
) {
    val context = LocalContext.current
    var uid by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var showSettingsDialog by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    val cameraPermissionState = rememberPermissionState(
        Manifest.permission.CAMERA
    )

    // 一開始請求user授權拍照
    LaunchedEffect(Unit) {
        if (!cameraPermissionState.status.isGranted) {
            cameraPermissionState.launchPermissionRequest()
        }
    }

    /* 呼叫rememberLauncherForActivityResult並搭配PickVisualMedia()
        以建立可以啟用photo picker的launcher物件。
        照片挑選完畢會執行onResult並傳來該照片的URI供後續處理 */
    val pickImageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = onImagePicked
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(0.7f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = uid,
                onValueChange = { uid = it },
                label = { Text(text = stringResource(id = R.string.uid)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = stringResource(id = R.string.password)) },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                // 如果使用者拍照完並接受該照片就會顯示在此頁面
                Image(
                    painter = if (imageUri == null) {
                        painterResource(id = R.drawable.android)
                    } else {
                        rememberAsyncImagePainter(imageUri)
                    },
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Column {
                    // 點擊拍照按鈕先檢查是否取得user授權，如果沒有就引導user去系統設定頁授權
                    TextButton(onClick = {
                        if (cameraPermissionState.status.isGranted) {
                            onTakePictureClick()
                        } else {
                            // 跳出dialog引導user去系統設定頁授權
                            showSettingsDialog = true
                        }
                    }) {
                        Text(text = stringResource(id = R.string.take_picture))
                    }
                    TextButton(onClick = {
                        pickImageLauncher.launch(
                            PickVisualMediaRequest(
                                // 設定只能挑選圖片
                                ActivityResultContracts.PickVisualMedia.ImageOnly
                            )
                        )
                    }) {
                        Text(text = stringResource(id = R.string.pick_image))
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    // 註冊個資
                    coroutineScope.launch {
                        // Convert URI to InputStream
                        val inputStream: InputStream? = imageUri?.let {
                            context.contentResolver.openInputStream(it)
                        }
                        val registered = userVM.uploadImageWithUser(uid, password, inputStream)
                        if (registered) {
                            onRegisterSuccess()
                        } else {
                            message = "Registering failed"
                        }
                        inputStream?.close()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.register))
            }

            Button(
                onClick = {
                    uid = ""
                    password = ""
                    message = ""
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.clear))
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "")
                TextButton(
                    onClick = {
                        // 切換回登入頁面
                        onBackToLogin()
                    },
                ) {
                    Text(text = stringResource(id = R.string.switch_login))
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = message)

        if (showSettingsDialog) {
            OpenSettingsDialog(
                onDismissRequest = { showSettingsDialog = false },
                onConfirm = {
                    openAppSettings(context)
                    showSettingsDialog = false
                },
                onDismiss = { showSettingsDialog = false }
            )
        }
    }
}

/** 引導user去系統設定頁授權 */
@Composable
fun OpenSettingsDialog(
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    // alert dialog屬於簡易、制式化的對話視窗
    AlertDialog(
        // 點擊對話視窗外部或back按鈕時呼叫，並非點擊dismissButton時呼叫
        onDismissRequest = onDismissRequest,
        title = { Text(text = stringResource(id = R.string.open_settings_title)) },
        text = { Text(text = stringResource(id = R.string.open_settings_text)) },
        // 設定確定按鈕
        confirmButton = {
            Button(onClick = onConfirm) {
                Text(text = stringResource(id = R.string.open_settings))
            }
        },
        // 設定取消按鈕
        dismissButton = {
            Button(onClick = onDismiss) {
                Text(text = stringResource(id = R.string.cancel))
            }
        }
    )
}

/** 開啟app設定 */
fun openAppSettings(context: Context) {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
        data = Uri.fromParts("package", context.packageName, null)
    }
    context.startActivity(intent)
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    BookshopDemo_AndroidTheme {
        RegisterScreen(
            onRegisterSuccess = {},
            onBackToLogin = {},
            onTakePictureClick = {},
            onImagePicked = {},
            imageUri = null
        )
    }
}
