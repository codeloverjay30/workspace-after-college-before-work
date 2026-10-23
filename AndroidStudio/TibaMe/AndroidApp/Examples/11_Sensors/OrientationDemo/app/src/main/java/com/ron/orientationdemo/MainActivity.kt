package com.ron.orientationdemo

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ron.orientationdemo.ui.theme.OrientationDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OrientationDemoTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    val context = LocalContext.current
    val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    var result by remember { mutableStateOf("") }

    // 實作監聽器偵測值的變化
    val sensorEventListener =
        object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                event?.let {
                    val sensor = event.sensor
                    val sensorInfo = StringBuilder()
                    sensorInfo.append("sensor name: ${sensor.name}\n")
                    sensorInfo.append("sensor type: ${sensor.type}\n")
                    sensorInfo.append("used power: ${sensor.power} mA\n")
                    val rotationMatrix = FloatArray(9)
                    // 將 rotation vector 轉成 rotation matrix
                    SensorManager.getRotationMatrixFromVector(rotationMatrix, event.values)

                    /* 由 rotation matrix 計算出裝置方位數值 (azimuth, pitch, roll)，單位弧度，
                        並存入 results */
                    val results = FloatArray(3)
                    SensorManager.getOrientation(rotationMatrix, results)

                    // 將弧度轉成角度顯示
                    sensorInfo.append("values (degrees):\n")
                    sensorInfo.append(" azimuth = ${Math.toDegrees(results[0].toDouble())}\n")
                    sensorInfo.append(" pitch = ${Math.toDegrees(results[1].toDouble())}\n")
                    sensorInfo.append(" roll = ${Math.toDegrees(results[2].toDouble())}\n")
                    result = sensorInfo.toString()
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
                // Not implemented
            }
        }


    LaunchedEffect(Unit) {
        // 替感應器註冊SensorEventListener
        val enable = sensorManager.registerListener(
            sensorEventListener,
            sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR),
            SensorManager.SENSOR_DELAY_NORMAL
        )
        if (!enable) {
            sensorManager.unregisterListener(sensorEventListener)
            result = "Rotation vector sensor is not supported!"
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            // 不需偵測感應器變化時要解除監聽器，以免耗電
            sensorManager.unregisterListener(sensorEventListener)
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = result)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    OrientationDemoTheme {
        Main()
    }
}