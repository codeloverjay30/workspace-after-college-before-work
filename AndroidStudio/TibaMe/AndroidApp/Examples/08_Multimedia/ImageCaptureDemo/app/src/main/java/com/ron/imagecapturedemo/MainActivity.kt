package com.ron.imagecapturedemo

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.ron.imagecapturedemo.ui.theme.Ex08_1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Ex08_1Theme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    // 請求使用者授權CAMERA項目，同意後即開啟拍照畫面
    RequestCameraPermission(onGrant = {
        MainScreen()
    })
}
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    NavHost(navController = navController, startDestination = "ImageCaptureScreen") {
        composable("ImageCaptureScreen") {
            ImageCaptureScreen(
                onTakePictureClick = { navController.navigate("CameraPreviewScreen") },
                imageUri = imageUri
            )
        }
        composable("CameraPreviewScreen") {
            CameraPreviewScreen(
                onPictureTaken = { uri: Uri? ->
                    imageUri = uri
                    navController.navigate("PicturePreviewScreen")
                },
                onCancelClick = {
                    imageUri = null
                    navController.popBackStack("ImageCaptureScreen", false)
                }
            )
        }
        composable("PicturePreviewScreen") {
            PicturePreviewScreen(
                imageUri = imageUri,
                // 點擊"Accept"按鈕回到首頁並顯示照片
                onAcceptClick = { navController.popBackStack("ImageCaptureScreen", false) },
                // 點擊"Reject"按鈕回前頁繼續拍攝
                onRejectClick = { navController.popBackStack() }
            )
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestCameraPermission(
    onGrant: @Composable () -> Unit
) {
    val context = LocalContext.current
    val cameraPermissionState = rememberPermissionState(
        Manifest.permission.CAMERA
    )

    if (cameraPermissionState.status.isGranted) {
        onGrant()
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(stringResource(id = R.string.request_permission_text))
            Button(onClick = {
                cameraPermissionState.launchPermissionRequest()
            }) {
                Text(stringResource(id = R.string.request_permission))
            }

            // 也可以引導使用者去設定頁面授權
            Text(stringResource(id = R.string.open_settings_text))
            Button(onClick = {
                openAppSettings(context.packageName, context)
            }) {
                Text(stringResource(id = R.string.open_settings))
            }
        }
    }
}

/** 開啟app設定 */
fun openAppSettings(packageName: String, context: Context) {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
        data = Uri.fromParts("package", packageName, null)
    }
    context.startActivity(intent)
}
