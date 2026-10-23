package com.ron.audioexternaldemo

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.ron.audioexternaldemo.ui.theme.AudioExternalDemoTheme
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AudioExternalDemoTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    val context = LocalContext.current
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }
    var audioPath by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // 播放音樂檔案(外部儲存體)
        Button(modifier = Modifier.fillMaxWidth(),
            onClick = {
                playAudio(mediaPlayer, audioPath)
            }
        ) {
            Text(text = stringResource(id = R.string.play_external))
        }
        // 播放音樂檔案(網路)
        Button(modifier = Modifier.fillMaxWidth(),
            onClick = {
                // 音樂檔案網址可能變更，如果無法播放，請自行替換成可播放的檔案網址
                val url =
                    "https://file-examples.com/storage/fe36b23e6a66fc0679c1f86/2017/11/file_example_MP3_700KB.mp3"
                playAudio(mediaPlayer, url)
            }
        ) {
            Text(text = stringResource(id = R.string.play_url))
        }
        Button(modifier = Modifier.fillMaxWidth(),
            onClick = {
                /* "mediaPlayer?.isPlaying"為"Boolean?"，
                    所以"== true"不可省略，"=="為equals函式 */
                if (mediaPlayer?.isPlaying == true) {
                    mediaPlayer?.stop()
                }
            }) {
            Text(text = stringResource(id = R.string.stop))
        }
    }

    LaunchedEffect(Unit) {
        // 將assets內的audio檔案複製到外部儲存體供之後播放
        audioPath = copyAudioToExternal(context)
        mediaPlayer = MediaPlayer()
    }

    // Release SoundPool and MediaPlayer when the composable leaves the composition
    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer?.release()
        }
    }
}

/** 播放audio檔案 */
private fun playAudio(mediaPlayer: MediaPlayer?, path: String) {
    val tag = "tag_playAudio"
    if (path.isEmpty()) {
        Log.e(tag, "Audio path is empty")
    }

    try {
        mediaPlayer?.let {
            // 重置MediaPlayer到尚未初始化狀態
            mediaPlayer.reset()
            // 指定audio檔案的位置
            mediaPlayer.setDataSource(path)
            // 指定audio檔案類型為音樂，使用者欲調整音量就必須調整音樂的音量
            mediaPlayer.setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build()
            )
            // 本機、遠端準備播放都可呼叫prepareAsync()，但要註冊監聽器，等到準備好了才播放
            mediaPlayer.prepareAsync()
            mediaPlayer.setOnPreparedListener { it.start() }
        }

    } catch (e: Exception) {
        Log.e(tag, e.toString())
    }
}

/** 將assets內的audio檔案複製到外部儲存體供之後播放 */
private fun copyAudioToExternal(context: Context): String {
    val tag = "tag_copyAudioToExternal"
    val fileName = "ring.mp3"
    val audioFile = File(context.getExternalFilesDir(null), fileName)

    if (!mediaMounted()) {
        return ""
    }

    // 如果檔案已經複製，就無需再複製，直接回傳路徑
    if (audioFile.exists()) {
        return audioFile.path
    }

    try {
        context.assets.open(fileName).use { input ->
            FileOutputStream(audioFile).use { output ->
                // 從assets目錄讀入audio檔案後複製到外部儲存體，並回傳複製後的檔案路徑
                val buffer = ByteArray(input.available())
                while (input.read(buffer) != -1) {
                    output.write(buffer)
                }
                return audioFile.path
            }
        }
    } catch (e: IOException) {
        Log.e(tag, e.toString())
        return ""
    }
}

/** 外部儲存體是否處於已掛載狀態(代表可讀寫資料) */
private fun mediaMounted(): Boolean {
    val state = Environment.getExternalStorageState()
    return state == Environment.MEDIA_MOUNTED
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AudioExternalDemoTheme {
        Main()
    }
}