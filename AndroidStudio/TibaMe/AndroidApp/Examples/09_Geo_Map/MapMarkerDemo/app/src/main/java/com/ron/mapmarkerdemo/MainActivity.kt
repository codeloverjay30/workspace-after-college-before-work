package com.ron.mapmarkerdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.ron.mapmarkerdemo.ui.theme.MapMarkerDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MapMarkerDemoTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    val taipei = LatLng(25.092713, 121.543442)
    // CameraPositionState用於儲存地圖鏡頭狀態
    val cameraPositionState = rememberCameraPositionState {
        // 移動地圖到指定位置
        this.position = CameraPosition.fromLatLngZoom(taipei, 15f)
    }
    // 儲存多個標記位置
    var positions by remember { mutableStateOf(listOf<LatLng>()) }
    // 暫存最新標記的位置，方便之後移動地圖至該標記
    var newPosition by remember { mutableStateOf<LatLng?>(null) }

    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            onMapLongClick = { latLng ->
                // 長按地圖就將該點位置加入到儲存標記的list
                positions = positions + latLng
                // 更新最新標記位置
                newPosition = latLng

            }
        ) {
            // 加一個標記
            Marker(
                /* 只加一個標記需呼叫rememberMarkerState()，否則會產生錯誤：
                Creating a state object during composition without using remember */
                state = rememberMarkerState(position = taipei),
                title = "Taipei City",
                icon = BitmapDescriptorFactory.fromResource(R.drawable.pin),
            )
            // 地圖加上多個標記
            positions.forEach { position ->
                Marker(
                    /* forEach區塊內不會有composition問題，所以使用MarkerState()而非rememberMarkerState()，
                       而且要從list移除position，也不能使用rememberMarkerState()，
                       否則會發生標記顯示與list內容不符情形 */
                    state = MarkerState(position = position),
                    title = "Marker",
                    snippet = "Lat: ${position.latitude}, Lng: ${position.longitude}",
                    // 長按訊息視窗就移除該標記
                    onInfoWindowLongClick = {
                        positions = positions - position
                    }
                )
            }
        }
        Button(modifier = Modifier
            .padding(8.dp)
            .align(Alignment.TopEnd),
            // 清除所有標記
            onClick = { positions = emptyList() }) {
            Text(text = "Clear All Makers")
        }
    }


    // 移動地圖至最新標記所在位置(newMarker一旦改變就會執行)
    LaunchedEffect(newPosition) {
        newPosition?.let {
            cameraPositionState.animate(
                CameraUpdateFactory.newLatLngZoom(it, 15f)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MapMarkerDemoTheme {
        Main()
    }
}