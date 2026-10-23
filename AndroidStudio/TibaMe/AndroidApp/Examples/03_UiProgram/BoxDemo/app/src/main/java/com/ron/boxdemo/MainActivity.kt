package com.ron.boxdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ron.boxdemo.ui.theme.BoxDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoxDemoTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(
            // 設定內容物對齊方式為置中對齊
            contentAlignment = Alignment.Center,
            modifier = Modifier.background(Color.Green)
        ) {
            Image(
                painter = painterResource(id = R.drawable.cat),
                contentDescription = "cat",
                modifier = Modifier.size(100.dp)
            )

            Text(
                text = "My cat",
                fontSize = 20.sp,
                color = Color.Blue,
            )
        }

        Spacer(modifier = Modifier.size(20.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.background(Color.Green)
        ) {
            Text(
                text = "My cat",
                fontSize = 20.sp,
                color = Color.Blue,
                // 子元件可以透過Modifier.align()來指定對齊父容器方式
                modifier = Modifier.align(Alignment.TopCenter)
            )

            Image(
                painter = painterResource(id = R.drawable.cat),
                contentDescription = "cat",
                modifier = Modifier.size(100.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BoxDemoTheme {
        Main()
    }
}