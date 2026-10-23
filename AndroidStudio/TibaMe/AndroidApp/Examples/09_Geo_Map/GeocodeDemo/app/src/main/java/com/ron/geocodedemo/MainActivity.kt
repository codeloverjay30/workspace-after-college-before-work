package com.ron.geocodedemo

import android.location.Geocoder
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
import com.ron.geocodedemo.ui.theme.GeocodeDemoTheme
import java.io.IOException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GeocodeDemoTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    val context = LocalContext.current
    var locationName by remember { mutableStateOf("") }
    var geocode by remember { mutableStateOf("") }
    var reverseGeocode by remember { mutableStateOf("") }

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
                /* API 33開始改呼叫getFromLocationName(String, int, Geocoder.GeocodeListener)
                    避免因等待結果而導致阻礙執行緒運行 */
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    // geocode
                    geocoder.getFromLocationName(locationName, 1) { addressList ->
                        // 因為maxResults指定1所以只取第一個address
                        val latitude = addressList[0].latitude
                        val longitude = addressList[0].longitude
                        geocode = "latitude: $latitude\nlongitude: $longitude\n"

                        // reverse geocode
                        geocoder.getFromLocation(latitude, longitude, 1) { addressReverseList ->
                            val addressReverse = addressReverseList[0]
                            val sb = StringBuilder()
                            for (i in 0..addressReverse.maxAddressLineIndex) {
                                sb.append(addressReverse.getAddressLine(i)).append("\n")
                            }
                            reverseGeocode = sb.toString()
                        }
                    }
                } else {
                    // 舊式寫法在API 33列為Deprecated
                    try {
                        // geocode
                        geocoder.getFromLocationName(locationName, 1)?.let { addressList ->
                            val latitude = addressList[0].latitude
                            val longitude = addressList[0].longitude
                            geocode = "latitude: $latitude\nlongitude: $longitude\n"

                            // reverse geocode
                            geocoder.getFromLocation(latitude, longitude, 1)
                                ?.let { addressReverseList ->
                                    val addressReverse = addressReverseList[0]
                                    val sb = StringBuilder()
                                    for (i in 0..addressReverse.maxAddressLineIndex) {
                                        sb.append(addressReverse.getAddressLine(i)).append("\n")
                                    }
                                    reverseGeocode = sb.toString()
                                }
                        } ?: {
                            geocode = "Location not found"
                        }

                    } catch (e: IOException) {
                        Log.e("tag_geocode", e.toString())
                    }
                }
            }) {
            Text(text = stringResource(id = R.string.geocoder))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow)
                .padding(8.dp),
            text = geocode
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Cyan)
                .padding(8.dp),
            text = reverseGeocode
        )
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GeocodeDemoTheme {
        Main()
    }
}