package com.ron.directdemo

import android.content.Context
import android.content.Intent
import android.location.Geocoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ron.directdemo.ui.theme.DirectDemoTheme
import java.io.IOException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DirectDemoTheme {
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
                        direct(context,
                            startLatitude,
                            startLongitude,
                            endLatitude,
                            endLongitude,
                            "walking",
                        )
                    }
                }
            }) {
            Text(text = stringResource(id = R.string.direct))
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                multipleDirect(
                    context,
                    25.0329690,
                    121.5654180,
                    22.6272780,
                    120.3014350,
                    listOf(24.9936280,121.3009800,24.1477360,120.6736480),
                    "driving",
                )
            }) {
            Text(text = stringResource(id = R.string.multiple_direct))
        }
    }
}

// 開啟內建地圖導航
private fun direct(
    context: Context,
    fromLat: Double, fromLng: Double, toLat: Double,
    toLng: Double,
    travelmode: String = "driving",
) {
    val uriStr = "https://www.google.com/maps/dir/?api=1" +
            "&origin=$fromLat,$fromLng&destination=$toLat,$toLng" +
            "&travelmode=$travelmode"
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uriStr))
    intent.setClassName(
        "com.google.android.apps.maps",
        "com.google.android.maps.MapsActivity"
    )
    context.startActivity(intent)
}

private fun multipleDirect(
    context: Context,
    originX: Double,
    originY: Double,
    destinationX: Double,
    destinationY: Double,
    waypoints: List<Double>,
    travelmode: String,
){
    // origin: Taipei, destination: Kaohsiung, waypoints: Taoyuan, Taichung
    val uriStr = "https://www.google.com/maps/dir/?api=1" +
            "&origin=$originX,$originY" +
            "&destination=$destinationX,$destinationY" +
            "&waypoints=${waypoints.joinToString { "|" }}" +
            "&travelmode=$travelmode"
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uriStr))
    intent.setClassName("com.google.android.apps.maps",
        "com.google.android.maps.MapsActivity")
    context.startActivity(intent)
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
    DirectDemoTheme {
        Main()
    }
}