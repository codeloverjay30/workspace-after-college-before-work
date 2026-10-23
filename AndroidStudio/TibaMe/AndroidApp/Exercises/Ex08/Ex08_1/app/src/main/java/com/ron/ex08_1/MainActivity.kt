package com.ron.ex08_1

import android.Manifest
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.ron.ex08_1.ui.theme.ImageCaptureDemoTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImageCaptureDemoTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    RequestCameraPermission(onGrant = {
        TakePicture()
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TakePicture() {
    val tag = "TAG_TakePicture"
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    var imageCapture: ImageCapture? by remember { mutableStateOf(null) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            factory = { ctx ->
                val previewView = PreviewView(ctx).also {
                    it.scaleType = PreviewView.ScaleType.FILL_CENTER
                }
                imageCapture = startCamera(context, lifecycleOwner, previewView)
                previewView
            },
            modifier = Modifier.fillMaxSize()
        )

        Button(
            onClick = {
                // 拍照相關設定
                val contentValues = ContentValues().apply {
                    // 設定檔名
                    put(MediaStore.MediaColumns.DISPLAY_NAME, "${System.currentTimeMillis()}.jpg")
                    // 設定照片格式
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
                    // 設定存檔路徑(sdcard/Pictures/CameraX-Image)
                    put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraX-Image")
                }

                // 設定欲將輸出結果存放在MediaStore，以方便其他app也可存取
                val outputOptions = ImageCapture.OutputFileOptions.Builder(
                    context.contentResolver,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    contentValues
                ).build()

                // 設定拍照監聽器，一旦拍照完畢即可取得照片
                imageCapture?.takePicture(
                    outputOptions,
                    ContextCompat.getMainExecutor(context),
                    object : ImageCapture.OnImageSavedCallback {
                        override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                            imageUri = outputFileResults.savedUri
                            Log.d(tag, "imageUri: $imageUri")
                            // 拍照存檔成功，bottom sheet顯示照片
                            imageUri?.let { showBottomSheet = true }
                        }

                        override fun onError(exception: ImageCaptureException) {
                            val msg = "Picture capture failed: ${exception.message}"
                            Log.e(tag, msg, exception)
                        }
                    }
                )
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            Text("Take Picture")
        }

        // bottom sheet顯示照片
        if (showBottomSheet) {
            ModalBottomSheet(
                // 使用者點擊bottom sheet以外區域時執行
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState,
                containerColor = colorResource(id = R.color.teal_200)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        /* 高度0.2代表佔20%。經由測試，值超過0.5仍舊與0.5高度一樣，
                            但可以向上滑動到指定高度 */
                        .fillMaxHeight(0.6f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = imageUri.toString(), fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Image(
                        painter = rememberAsyncImagePainter(
                            ImageRequest.Builder(context)
                                .data(imageUri)
                                .size(Size.ORIGINAL)
                                .build()
                        ),
                        contentDescription = "Captured Image",
                        modifier = Modifier
                            .fillMaxSize(0.5f)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showBottomSheet = false
                            }
                        }
                    }) {
                        Text("OK")
                    }
                }
            }
        }
    }
}

private fun startCamera(
    context: Context,
    lifecycleOwner: LifecycleOwner,
    previewView: PreviewView
): ImageCapture {
    // 單例模式取得物件，用來連結相機生命週期
    val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
    val imageCapture = ImageCapture.Builder().build()
    // 註冊監聽器，當future結束，會使用指定executor執行監聽器內容
    cameraProviderFuture.addListener({
        val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
        // 建立預覽use case。CameraX將相機功能如預覽、拍照都視為是一種use case
        val preview = Preview.Builder()
            .build()
            .also {
                // 取得Preview.SurfaceProvider，並用以顯示預覽資料
                it.surfaceProvider = previewView.surfaceProvider
            }

        // 選擇後鏡頭
        val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

        try {
            // 先取消所有之前對鏡頭的連結使用
            cameraProvider.unbindAll()
            // 從後鏡頭擷取影像資料，並呈現預覽結果；也可用於拍照
            cameraProvider.bindToLifecycle(
                lifecycleOwner, // 生命週期物件，用來掌控相機使用過程，例如預覽、拍照
                cameraSelector, // 使用cameraSelector選擇的鏡頭擷取影像資料
                preview, // 設定要使用預覽use case
                imageCapture // 設定要使用拍照use case
            )
        } catch (exc: Exception) {
            Log.e("CameraPreview", "Use case binding failed", exc)
        }
    }, ContextCompat.getMainExecutor(context))

    return imageCapture
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