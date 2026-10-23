package com.jay30.volumeadjuster.device.settings.volume

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun VolumeAdjusterScreen(){
    val currentContext = LocalContext.current

    val volumeAdjuster by remember { mutableStateOf(VolumeAdjuster(currentContext) ) }
    var buttonMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text("${volumeAdjuster.volumeLevel.value}")
        Spacer(modifier = Modifier.height(20.dp).fillMaxWidth())
        Text("${volumeAdjuster.volumePercent}")
        Spacer(modifier = Modifier.height(20.dp).fillMaxWidth())
        Text("${volumeAdjuster.maxVolumeLevel.value}")
        Spacer(modifier = Modifier.height(20.dp).fillMaxWidth())
        Row {
            Button(
                onClick = {
                    volumeAdjuster.increaseVolume()
                    buttonMessage = "The plus button was clicked."
                }
            ) {
                Text("+")
            }

            Button(
                onClick = {
                    volumeAdjuster.decreaseVolume()
                    buttonMessage = "The minus button was clicked."
                }
            ) {
                Text("-")
            }
        }

        Text(buttonMessage)
    }
}