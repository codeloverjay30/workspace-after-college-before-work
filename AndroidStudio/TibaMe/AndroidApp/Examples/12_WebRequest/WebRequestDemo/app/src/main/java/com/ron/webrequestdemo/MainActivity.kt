package com.ron.webrequestdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.ron.webrequestdemo.ui.theme.WebRequestDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WebRequestDemoTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main(myViewModel: MyViewModel = viewModel()) {
    val text by myViewModel.text.collectAsState()
    var showText by remember { mutableStateOf(false) }
    var showImage by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                myViewModel.getText()
                showText = true
                showImage = false
            },
            modifier = Modifier.fillMaxWidth(0.7f)
        ) {
            Text(text = stringResource(id = R.string.remote_text))
        }
        Button(
            onClick = {
                showImage = true
                showText = false
            },
            modifier = Modifier.fillMaxWidth(0.7f)
        ) {
            Text(text = stringResource(id = R.string.remote_image))
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (showText) {
            Text(text = text)
        }

        if (showImage) {
            val url =
                "https://images.pexels.com/photos/14306688/pexels-photo-14306688.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
            Image(
                painter = rememberAsyncImagePainter(model = url),
                contentDescription = "remote image"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WebRequestDemoTheme {
        Main()
    }
}