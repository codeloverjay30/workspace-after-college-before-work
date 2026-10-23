package com.ron.camerapreviewdemo

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.ron.camerapreviewdemo.ui.theme.CameraPreviewDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CameraPreviewDemoTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    // 請求使用者授權CAMERA項目，同意後即開啟相機預覽畫面
    RequestCameraPermission(onGrant = {
        CameraPreview(modifier = Modifier.fillMaxSize())
    })
}

@Composable
fun CameraPreview(
    modifier: Modifier = Modifier
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    // 建立Android View呈現預覽畫面
    AndroidView(
        factory = { ctx ->
            /* PreviewView用來呈現CameraX的預覽畫面，
                PreviewView繼承FrameLayout所以需要使用AndroidView呈現 */
            val previewView = PreviewView(ctx).also {
                // 設定相機截取畫面填滿整個預覽畫面
                it.scaleType = PreviewView.ScaleType.FILL_CENTER
            }
            startCamera(ctx, lifecycleOwner, previewView)
            previewView
        },
        modifier = modifier
    )
}

/** 開啟相機功能 */
private fun startCamera(
    context: Context,
    lifecycleOwner: LifecycleOwner,
    previewView: PreviewView
) {
    // 單例模式取得物件，用來連結相機生命週期
    val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
    // 註冊監聽器，當future結束，會使用指定executor執行監聽器內容
    cameraProviderFuture.addListener({
        val cameraProvider = cameraProviderFuture.get()
        // 建立預覽use case。CameraX將相機功能如預覽、拍照都視為是一種use case
        val preview = Preview.Builder()
            .build()
            .also {
                // 取得Preview.SurfaceProvider，並用以顯示預覽資料
                it.setSurfaceProvider(previewView.surfaceProvider)
            }

        // 選擇後鏡頭
        val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

        try {
            // 先取消所有之前對鏡頭的連結使用
            cameraProvider.unbindAll()
            // 從後鏡頭擷取影像資料，並呈現預覽結果
            cameraProvider.bindToLifecycle(
                lifecycleOwner, // 生命週期物件，用來掌控相機使用過程，例如預覽、拍照
                cameraSelector, // 使用cameraSelector選擇的鏡頭擷取影像資料
                preview // 設定要使用預覽use case
            )
        } catch (exc: Exception) {
            Log.e("CameraPreview", "Use case binding failed", exc)
            Toast.makeText(context, "Camera failed to start", Toast.LENGTH_SHORT).show()
        }
    }, ContextCompat.getMainExecutor(context))
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

// 因為相機預覽類別Preview跟下列android預覽的類別名稱相同，易造成import衝突
// 加上Android預覽不支援相機功能，建議刪除下列Android預覽程式
//@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    CameraPreviewDemoTheme {
//        Main()
//    }
//}