package com.ron.distancedemo

import android.location.Geocoder
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.io.IOException
import com.ron.distancedemo.ui.theme.DistanceDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DistanceDemoTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    val context = LocalContext.current
    var start by remember { mutableStateOf("") }
    var end by remember { mutableStateOf("") }
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = start,
            onValueChange = { start = it },
            label = {
                Text(
                    text = stringResource(id = R.string.starting_point)
                )
            })
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = end,
            onValueChange = { end = it },
            label = {
                Text(
                    text = stringResource(id = R.string.end_point)
                )
            })
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                val geocoder = Geocoder(context)
                // 先取得起點經緯度，再取得終點經緯度，然後才能計算2點距離
                geocode(geocoder, start) { startLatitude, startLongitude ->
                    geocode(geocoder, end) { endLatitude, endLongitude ->
                        // 計算結果會存入float陣列，單位為公尺
                        val results = FloatArray(1)
                        Location.distanceBetween(
                            startLatitude,
                            startLongitude,
                            endLatitude,
                            endLongitude,
                            results
                        )
                        text = "Distance (meters): ${results[0]}"
                    }
                }
            }) {
            Text(text = stringResource(id = R.string.distance))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow)
                .padding(8.dp),
            text = text
        )
    }
}

/**
 * 將地名轉成經緯度
 */
private fun geocode(
    geocoder: Geocoder,
    locationName: String,
    onComplete: (Double, Double) -> Unit
) {
    /* API 33開始改呼叫getFromLocationName(String, int, Geocoder.GeocodeListener)
        避免因等待結果而導致阻礙執行緒運行 */
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        // geocode
        geocoder.getFromLocationName(locationName, 1) { addressList ->
            // 因為maxResults指定1所以只取第一個address
            val latitude = addressList[0].latitude
            val longitude = addressList[0].longitude
            onComplete(latitude, longitude)
        }
    } else {
        // 舊式寫法在API 33列為Deprecated
        try {
            // geocode
            geocoder.getFromLocationName(locationName, 1)?.let { addressList ->
                val latitude = addressList[0].latitude
                val longitude = addressList[0].longitude
                onComplete(latitude, longitude)
            }
        } catch (e: IOException) {
            Log.e("tag_geocode", e.toString())
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DistanceDemoTheme {
        Main()
    }
}