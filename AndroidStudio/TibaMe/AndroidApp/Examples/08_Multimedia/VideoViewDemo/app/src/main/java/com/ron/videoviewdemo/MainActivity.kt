package com.ron.videoviewdemo

import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.ron.videoviewdemo.ui.theme.VideoViewDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VideoViewDemoTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Define the VideoView within AndroidView
        AndroidView(
            factory = { context ->
                // apply區塊內可省略現行VideoView物件名稱
                VideoView(context).apply {
                    val path = "android.resource://${context.packageName}/${R.raw.sample}"
                    setVideoURI(Uri.parse(path))
                    setMediaController(MediaController(context))
                    // video播放完畢時執行lambda內容
                    setOnCompletionListener {
                        message = context.getString(R.string.video_finished_playing)
                    }
                    start()
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = message)
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VideoViewDemoTheme {
        Main()
    }
}