package com.jay30.volumeadjuster.device.settings.volume

import android.content.Context
import android.content.Context.AUDIO_SERVICE
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

class VolumeAdjuster(
    private val currentContext: Context,
) {

    val mediaPlayer = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            MediaPlayer(currentContext)
        } else {
            MediaPlayer()
        }

    val audioManager: AudioManager
        get() = currentContext.getSystemService(AUDIO_SERVICE) as AudioManager

    val volumeLevel : MutableState<Int> @Composable
        get() = remember { mutableIntStateOf(
            audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
        )}

    val maxVolumeLevel : MutableState<Int> @Composable
        get() = remember { mutableIntStateOf(
            audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        )}

    val volumePercent: Int @Composable
        get() = (volumeLevel.value.toFloat() / maxVolumeLevel.value * 100).toInt()

    fun increaseVolume(){
        mediaPlayer.setVolume(volumePercent/100)
    }

    fun decreaseVolume(){
        audioManager.adjustVolume(
            AudioManager.ADJUST_LOWER,
            AudioManager.FLAG_PLAY_SOUND
        )
    }

}