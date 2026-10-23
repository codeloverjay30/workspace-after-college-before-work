package com.ron.audioresdemo

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.SoundPool
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ron.audioresdemo.ui.theme.AudioResDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AudioResDemoTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    val context = LocalContext.current
    var soundPool by remember { mutableStateOf<SoundPool?>(null) }
    var soundId by remember { mutableIntStateOf(0) }
    var streamId by remember { mutableIntStateOf(0) }
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }
    // 取得AudioManager服務物件
    val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

    LaunchedEffect(Unit) {
        soundPool = SoundPool.Builder().build()
        // 可以正常載入音效檔時，儲存該sound ID(否則儲存0)，播放時需要指定sound ID
        soundId = soundPool?.load(context, R.raw.sound, 1) ?: 0

        // 建立MediaPlayer物件
        mediaPlayer = MediaPlayer.create(context, R.raw.music)

    }

    // Release SoundPool and MediaPlayer when the composable leaves the composition
    DisposableEffect(Unit) {
        onDispose {
            soundPool?.release()
            mediaPlayer?.release()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // 音效按鈕組
        Button(modifier = Modifier.fillMaxWidth(),
            onClick = {
                // 播放音效時會回傳stream ID，停止播放時需要指定stream ID
                streamId = soundPool?.play(
                    soundId, 1f, 1f, 0, 0, 1f
                ) ?: 0
            }
        ) {
            Text(text = stringResource(id = R.string.play_sound))
        }
        Button(modifier = Modifier.fillMaxWidth(),
            onClick = {
                // 利用stream ID停止播放音效
                soundPool?.stop(streamId)
            }) {
            Text(text = stringResource(id = R.string.stop_sound))
        }
        Spacer(modifier = Modifier.height(16.dp))

        // 音樂按鈕組
        Button(modifier = Modifier.fillMaxWidth(),
            onClick = {
                if (mediaPlayer?.isPlaying == false) {
                    mediaPlayer?.start()
                }
            }
        ) {
            Text(text = stringResource(id = R.string.play_music))
        }
        Button(modifier = Modifier.fillMaxWidth(),
            onClick = {
                /* "mediaPlayer?.isPlaying"為"Boolean?"，
                    所以"== true"不可省略，"=="為equals函式 */
                if (mediaPlayer?.isPlaying == true) {
                    mediaPlayer?.pause()
                }
            }
        ) {
            Text(text = stringResource(id = R.string.pause_music))
        }
        Button(modifier = Modifier.fillMaxWidth(),
            onClick = {
                /* "mediaPlayer?.isPlaying"為"Boolean?"，
                    所以"== true"不可省略，"=="為equals函式 */
                if (mediaPlayer?.isPlaying == true) {
                    mediaPlayer?.stop()
                    // 準備播放。本機播放可呼叫prepare()；遠端播放則建議呼叫prepareAsync()
                    mediaPlayer?.prepare()
                }
            }) {
            Text(text = stringResource(id = R.string.stop_music))
        }
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(modifier = Modifier.weight(1f),
                onClick = {
                    // 調降音量
                    audioManager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI)
                }
            ) {
                Text(text = stringResource(id = R.string.lower))
            }
            Button(modifier = Modifier.weight(1f),
                onClick = {
                    // 靜音
                    audioManager.adjustVolume(AudioManager.ADJUST_MUTE, AudioManager.FLAG_SHOW_UI)
                }
            ) {
                Text(text = stringResource(id = R.string.mute))
            }
            Button(modifier = Modifier.weight(1f),
                onClick = {
                    // 調升音量
                    audioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI)
                }) {
                Text(text = stringResource(id = R.string.raise))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AudioResDemoTheme {
        Main()
    }
}