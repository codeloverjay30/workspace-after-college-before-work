package com.ron.viewhidedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ron.viewhidedemo.ui.theme.ViewHideDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViewHideDemoTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    var bearShow by remember { mutableStateOf(true) }
    var catShow by remember { mutableStateOf(true) }
    var mouseShow by remember { mutableStateOf(true) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)

    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            if (bearShow) {
                Image(
                    painter = painterResource(id = R.drawable.bear),
                    contentDescription = "bear",
                    modifier = Modifier
                        .size(80.dp)
                        // 設定image可處理點擊事件
                        .clickable { bearShow = false }
                )
            }

            if (catShow) {
                Image(
                    painter = painterResource(id = R.drawable.cat),
                    contentDescription = "cat",
                    modifier = Modifier
                        .size(80.dp)
                        .clickable { catShow = false }
                )
            }

            if (mouseShow) {
                Image(
                    painter = painterResource(id = R.drawable.mouse),
                    contentDescription = "mouse",
                    modifier = Modifier
                        .size(80.dp)
                        .clickable { mouseShow = false }
                )
            }
        }

        Button(
            onClick = {
                bearShow = true
                catShow = true
                mouseShow = true
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Reset")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ViewHideDemoTheme {
        Main()
    }
}