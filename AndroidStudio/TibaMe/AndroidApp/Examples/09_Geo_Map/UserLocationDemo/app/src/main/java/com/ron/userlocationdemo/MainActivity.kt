package com.ron.userlocationdemo

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.ron.userlocationdemo.ui.theme.UserLocationDemoTheme
import java.text.DateFormat
import java.util.Date

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UserLocationDemoTheme {
                Main()
            }
        }
    }
}

@SuppressLint("MissingPermission")
@Composable
fun Main() {
    // 請求使用者授權ACCESS_FINE_LOCATION項目，同意後即開啟使用者位置資訊畫面
    RequestAccessLocationPermission(onGrant = {
        UserLocationDemo()
    })
}

@SuppressLint("MissingPermission")
@Composable
fun UserLocationDemo() {
    val context = LocalContext.current
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    // 使用com.google.accompanist:accompanist-permissions功能可以簡化授權過程
    var text by remember { mutableStateOf("") }

    // 設定精準度、頻率、最低位移距離
    val locationRequest = LocationRequest.Builder(
        Priority.PRIORITY_HIGH_ACCURACY,
        10000
    ).setMinUpdateDistanceMeters(1000f).build()

    val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            val location = locationResult.lastLocation
            text = if (location == null) {
                "Fetching location..."
            } else {
                userLocationInfo(location)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                // 取得裝置最後記錄位置
                fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                    text = if (location == null) {
                        "Fetching location..."
                    } else {
                        userLocationInfo(location)
                    }
                }

                // 持續取得最新位置。Looper.getMainLooper()代表以主執行緒呼叫callback方法
                fusedLocationClient.requestLocationUpdates(
                    locationRequest, locationCallback, Looper.getMainLooper()
                )
            }
        ) {
            Text(text = stringResource(id = R.string.My_Location))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text)
    }

    DisposableEffect(Unit) {
        // 更新位置會耗資源，所以離開畫面就停止更新位置
        onDispose {
            fusedLocationClient.removeLocationUpdates(locationCallback)
        }
    }
}

/**
 * 取得使用者位置詳細資訊
 */
private fun userLocationInfo(lastLocation: Location): String {
    var message = "The Information of the Last Location \n"
    val date = Date(lastLocation.time)
    val dateFormat = DateFormat.getDateTimeInstance()
    val time = dateFormat.format(date)
    message += "fix time: $time\n"
    message += "latitude: ${lastLocation.latitude}\n"
    message += "longitude: ${lastLocation.longitude}\n"
    message += "accuracy (meters): ${lastLocation.accuracy}\n"
    message += "altitude (meters): ${lastLocation.altitude}\n"
    message += "bearing (horizontal direction- in degrees): ${lastLocation.bearing}\n"
    message += "speed (meters/second): ${lastLocation.speed}\n"
    return message
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestAccessLocationPermission(
    onGrant: @Composable () -> Unit
) {
    val context = LocalContext.current
    val permissionState = rememberPermissionState(
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    if (permissionState.status.isGranted) {
        onGrant()
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(stringResource(id = R.string.request_permission_text))
            Button(onClick = {
                permissionState.launchPermissionRequest()
            }) {
                Text(stringResource(id = R.string.request_permission))
            }

            // 也可以引導使用者去設定頁面授權
            Text(stringResource(id = R.string.open_settings_text))
            Button(onClick = {
                openAppSettings(context.packageName, context)
            }) {
                Text(stringResource(id = R.string.open_settings))
            }
        }
    }
}

/** 開啟app設定 */
fun openAppSettings(packageName: String, context: Context) {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
        data = Uri.fromParts("package", packageName, null)
    }
    context.startActivity(intent)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    UserLocationDemoTheme {
        Main()
    }
}