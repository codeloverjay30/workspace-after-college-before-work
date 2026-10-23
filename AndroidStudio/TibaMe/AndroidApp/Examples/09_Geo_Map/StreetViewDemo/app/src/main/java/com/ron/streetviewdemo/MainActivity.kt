package com.ron.streetviewdemo

import android.content.Context
import android.content.Intent
import android.location.Geocoder
import android.net.Uri
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
import com.ron.streetviewdemo.ui.theme.StreetViewDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StreetViewDemoTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    val context = LocalContext.current
    var locationName by remember { mutableStateOf("") }
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = locationName,
            onValueChange = { locationName = it },
            label = {
                Text(
                    text = stringResource(id = R.string.location_name_Address)
                )
            })
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                val geocoder = Geocoder(context)
                geocode(geocoder, locationName) { latitude, longitude ->
                    text = "latitude: $latitude\nlongitude: $longitude\n"
                    openStreetView(context, latitude, longitude)
                }
            }) {
            Text(text = stringResource(id = R.string.street_view))
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
private fun geocode(geocoder: Geocoder, locationName: String, onComplete: (Double, Double) -> Unit) {
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

/**
 * 使用內建Google地圖開啟街景圖
 */
private fun openStreetView(context: Context, latitude: Double, longitude: Double) {
    val uriStr = "google.streetview:cbll=$latitude,$longitude"
    // ACTION_VIEW代表呈現資料給使用者
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uriStr))
    // 指定package name以開啟內建Google地圖
    intent.setPackage("com.google.android.apps.maps")
    context.startActivity(intent)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StreetViewDemoTheme {
        Main()
    }
}