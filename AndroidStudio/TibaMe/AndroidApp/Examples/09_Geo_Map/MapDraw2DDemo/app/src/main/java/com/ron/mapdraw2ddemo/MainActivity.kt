package com.ron.mapdraw2ddemo

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Circle
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polygon
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import com.ron.mapdraw2ddemo.ui.theme.MapDraw2DDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MapDraw2DDemoTheme {
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
    var positions by remember { mutableStateOf(listOf(taipei)) }
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
            // 地圖加上多個標記
            positions.forEach { latLng ->
                Marker(
                    /* forEach區塊內不會有composition問題，所以使用MarkerState()而非rememberMarkerState()，
                       而且要從list移除position，也不能使用rememberMarkerState()，
                       否則會發生標記顯示與list內容不符情形 */
                    state = MarkerState(position = latLng),
                    title = "Marker",
                    snippet = "Lat: ${latLng.latitude}, Lng: ${latLng.longitude}",
                    // 長按訊息視窗就移除該標記
                    onInfoWindowLongClick = {
                        positions = positions - latLng
                    }
                )
                Circle(
                    center = latLng,
                    radius = 200.0,
                    strokeWidth = 1f,
                    strokeColor = Color.Transparent,
                    fillColor = Color(0, 0, 100, 50)
                )
            }

            Polyline(
                points = positions,
                color = Color.Green,
                width = 10f
            )

            Polygon(
                points = positions,
                strokeWidth = 2f,
                strokeColor = Color.Red,
                fillColor = Color(100, 100, 0, 50)
            )


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
    MapDraw2DDemoTheme {
        Main()
    }
}