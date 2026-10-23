package com.ron.mapbasicdemo

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import com.ron.mapbasicdemo.ui.theme.MapBasicDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MapBasicDemoTheme {
                Main()
            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Main() {
    val context = LocalContext.current
    val target = LatLng(25.092713, 121.543442)
    // CameraPositionState用於儲存地圖鏡頭狀態
    val cameraPositionState = rememberCameraPositionState {
        // 移動地圖到指定位置
        this.position = CameraPosition.fromLatLngZoom(target, 15f)
    }

    val locationPermission = rememberPermissionState(
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    LaunchedEffect(Unit) {
        if (!locationPermission.status.isGranted) {
            locationPermission.launchPermissionRequest()
        }
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        // 屬性相關設定
        properties = MapProperties(
            // 是否可呈現我的位置(需取得授權)
            isMyLocationEnabled = locationPermission.status.isGranted,
            // 是否呈現交通圖
            isTrafficEnabled = true,
            // 設定可捲動的範圍
            latLngBoundsForCameraTarget = LatLngBounds(
                LatLng(22.045858, 119.426224),
                LatLng(25.161124, 122.343094)
            ),
            // 設定地圖種類：NORMAL(一般圖，預設)、HYBRID(混合圖)、SATELLITE(衛星圖)、TERRAIN(地形圖)
            mapType = MapType.NORMAL,
            // 設定放大上限
            maxZoomPreference = 20f,
            // 設定縮小下限
            minZoomPreference = 5f
        ),
        // UI相關設定
        uiSettings = MapUiSettings(
            // 顯示指北針
            compassEnabled = true,
            // 顯示我的位置按鈕
            myLocationButtonEnabled = locationPermission.status.isGranted,
            // 允許旋轉手勢
            rotationGesturesEnabled = true,
            // 允許滑動手勢
            scrollGesturesEnabled = true,
            // 允許旋轉或縮放時可同時使用滑動手勢
            scrollGesturesEnabledDuringRotateOrZoom = true,
            // 開啟地圖傾斜手勢
            tiltGesturesEnabled = true,
            // 顯示縮放按鈕
            zoomControlsEnabled = true,
            // 允許縮放手勢
            zoomGesturesEnabled = true
        ),
        // 地圖載入完成後執行
        onMapLoaded = {
            Toast.makeText(context, "Map Loaded", Toast.LENGTH_SHORT).show()
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MapBasicDemoTheme {
        Main()
    }
}