package com.jay30.vibratorcontroller

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jay30.vibratorcontroller.ui.theme.VibratorControllerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VibratorControllerTheme {
                Main()
            }
        }
    }
}


@Composable
fun Main(){
    VibratorController(LocalContext.current)
}

@Composable
fun VibratorController(
    currentContext: Context,
) {
    val vibrator = currentContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

    var isActive by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedButton(
            onClick = {
                vibrator.vibrate(
                    VibrationEffect.createOneShot(
                        500L,
                        VibrationEffect.DEFAULT_AMPLITUDE
                    )
                )
            }
        ) {
            Text(text = "Vibrate")
        }

        FilledIconToggleButton(
            checked = isActive,
            onCheckedChange = {
                isActive = !isActive
                if (isActive) {
                    vibrator.vibrate(
                        VibrationEffect.createWaveform(
                            longArrayOf(0, 500, 300), // timings
                            1 // start repeating from index 1
                        )
                    )
                } else {
                    vibrator.cancel()
                }

            },
            modifier = Modifier.padding(top = 32.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Toggle Notification"
            )
        }

        // Buttons for different vibration effects
        Button(
            onClick = {
                vibrator.vibrate(
                    VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK)
                )
            },
            modifier = Modifier.padding(top = 64.dp)
        ) {
            Text(text = "Click Effect")
        }
        Button(
            onClick = {
                vibrator.vibrate(
                    VibrationEffect.createPredefined(VibrationEffect.EFFECT_HEAVY_CLICK)
                )
            },
            modifier = Modifier.padding(vertical = 32.dp)
        ) {
            Text(text = "Heavy Click Effect")
        }
        Button(
            onClick = {
                vibrator.vibrate(
                    VibrationEffect.createPredefined(VibrationEffect.EFFECT_DOUBLE_CLICK)
                )
            }
        ) {
            Text(text = "Double Click Effect")
        }
    }
}