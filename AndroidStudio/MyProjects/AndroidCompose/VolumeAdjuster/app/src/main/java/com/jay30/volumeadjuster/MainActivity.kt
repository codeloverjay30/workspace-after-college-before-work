package com.jay30.volumeadjuster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jay30.volumeadjuster.device.settings.volume.VolumeAdjuster
import com.jay30.volumeadjuster.device.settings.volume.VolumeAdjusterScreen
import com.jay30.volumeadjuster.ui.theme.VolumeAdjusterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VolumeAdjusterTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main(){
    VolumeAdjusterScreen()
}