package com.ron.recordaudiodemo

import android.Manifest
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.ron.recordaudiodemo.ui.theme.RecordAudioDemoTheme
import java.io.File

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Main()
        }
    }
}

@Composable
fun Main() {
    // 請求使用者授權RECORD_AUDIO項目，同意後即開啟錄音畫面
    RequestRecordAudioPermission(onGrant = {
        RecordAudioComposable(modifier = Modifier.fillMaxSize())
    })
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestRecordAudioPermission(
    onGrant: @Composable () -> Unit
) {
    val context = LocalContext.current
    val recordPermissionState = rememberPermissionState(
        Manifest.permission.RECORD_AUDIO
    )

    if (recordPermissionState.status.isGranted) {
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
                recordPermissionState.launchPermissionRequest()
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

@Composable
fun RecordAudioComposable(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var mediaRecorder by remember { mutableStateOf<MediaRecorder?>(null) }
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }
    var audioPath by remember { mutableStateOf("") }
    // 是否錄音中
    var recording by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.padding(16.dp)
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                audioPath = File(context.getExternalFilesDir(null), "sample.3gp").path
                recordAudio(mediaRecorder, audioPath)
                recording = true
            },
            // 開始錄音時，Record按鈕就會反白不能使用
            enabled = !recording
        ) {
            Text(text = stringResource(id = R.string.Record))
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                // 結束錄音
                mediaRecorder?.stop()
                recording = false
            },
            // 結束錄音時，Stop Record按鈕就會反白不能使用
            enabled = recording
        ) {
            Text(text = stringResource(id = R.string.stop_record))
        }
        Button(modifier = Modifier.fillMaxWidth(),
            onClick = {
                playAudio(mediaPlayer, audioPath)
            }
        ) {
            Text(text = stringResource(id = R.string.play))
        }
        Button(modifier = Modifier.fillMaxWidth(),
            onClick = {
                if (mediaPlayer?.isPlaying == true) {
                    mediaPlayer?.stop()
                }
            }) {
            Text(text = stringResource(id = R.string.stop_play))
        }
    }

    // 一開始就建立MediaRecorder與MediaPlayer物件
    LaunchedEffect(Unit) {
        // API 31 MediaPlayer()列為deprecated，要改呼叫MediaRecorder(context)
        mediaRecorder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            MediaRecorder(context)
        } else {
            MediaRecorder()
        }

        mediaPlayer = MediaPlayer()
    }

    // Release MediaRecorder and MediaPlayer when the composable leaves the composition
    DisposableEffect(Unit) {
        onDispose {
            // 釋放MediaRecorder佔用的資源
            mediaRecorder?.release()
            mediaPlayer?.release()
        }
    }
}

/** 錄製audio檔案 */
private fun recordAudio(mediaRecorder: MediaRecorder?, path: String): Boolean {
    val tag = "tag_recordAudio"
    // apply區塊內可省略對應物件名稱
    mediaRecorder?.apply {
        // 重複使用MediaRecorder物件，但必須重新設定
        reset()
        // 設定錄音來源
        setAudioSource(MediaRecorder.AudioSource.MIC)
        // 設定錄音輸出格式 (例如3GPP)
        setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
        // 設定錄音編碼方式 (例如AMR_NB)
        setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
        // 設定錄音檔案的存放位置
        setOutputFile(path)
        try {
            // 準備錄音
            prepare()
            // 開始錄音
            start()
        } catch (e: Exception) {
            Log.e(tag, e.toString())
            return false
        }
    }
    return true
}

/** 播放audio檔案 */
private fun playAudio(mediaPlayer: MediaPlayer?, path: String) {
    val tag = "tag_playAudio"

    if (path.isEmpty()) {
        Log.e(tag, "Audio path is empty")
        return
    }

    try {
        mediaPlayer?.apply {
            reset()
            // 指定audio檔案的位置
            setDataSource(path)
            // 指定audio檔案類型為音樂，使用者欲調整音量就必須調整音樂的音量
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build()
            )
            // 準備播放，MediaPlayer需要註冊監聽器，等到準備好了才播放
            prepareAsync()
            setOnPreparedListener { it.start() }
        }
    } catch (e: Exception) {
        Log.e(tag, e.toString())
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RecordAudioDemoTheme {
        Main()
    }
}