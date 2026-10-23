package com.ron.progressindicatordemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ron.progressindicatordemo.ui.theme.ProgressIndicatorDemoTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProgressIndicatorDemoTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp, vertical = 32.dp)
    ) {
        LinearProgress()
        CircularProgress()
    }

}

// 線形進度指標畫面
@Composable
fun LinearProgress() {
    var currentProgress by remember { mutableFloatStateOf(0f) }
    var loading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope() // Create a coroutine scope

    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            // 點擊按鈕:顯示線形進度指標 > 持續增加progress值 > 結束顯示
            onClick = {
                loading = true
                scope.launch {
                    loadProgress { progress ->
                        currentProgress = progress
                    }
                    // 當下載模擬結束，將loading設為false，按鈕恢復成可使用狀態
                    loading = false
                }
            },
            // 設定按鈕是否可使用
            enabled = !loading
        ) {
            Text("Start loading (Linear)")
        }

        // loading為true就顯示進度指標
        if (loading) {
            LinearProgressIndicator(
                progress = { currentProgress },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

/** 持續改變progress值 */
suspend fun loadProgress(updateProgress: (Float) -> Unit) {
    for (i in 1..10) {
        updateProgress(i.toFloat() / 10)
        delay(10)
    }
}

// 圓形進度指標畫面
@Composable
fun CircularProgress() {
    var loading by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // 開始下載按鈕
            Button(onClick = { loading = true }, enabled = !loading) {
                Text("Start loading (Circular)")
            }
            // 結束下載按鈕
            Button(onClick = { loading = false }, enabled = loading) {
                Text("Stop loading")
            }
        }

        // loading為true就顯示圓形進度指標
        if (loading) {
            CircularProgressIndicator(
                modifier = Modifier.width(64.dp),
                color = Color.DarkGray,
                trackColor = Color.LightGray,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProgressIndicatorDemoTheme {
        Main()
    }
}