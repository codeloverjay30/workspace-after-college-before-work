package com.ron.customdialogdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.ron.customdialogdemo.ui.theme.CustomDialogDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomDialogDemoTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    var showDialog by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf("Attraction data ...") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(text = message)
        Button(onClick = { showDialog = true }) {
            Text("Delete Data")
        }
        if (showDialog) {
            DialogWithImage(
                painter = painterResource(id = R.drawable.image),
                contentDescription = "image",
                onConfirmation = {
                    message = "Data deleted!"
                    showDialog = false
                },
                onDismissRequest = {
                    message = "Cancelled"
                    showDialog = false
                }
            )
        }
    }
}

@Composable
fun DialogWithImage(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    painter: Painter,
    contentDescription: String,
) {
    // 自訂Dialog內容
    Dialog(onDismissRequest = onDismissRequest) {
        // card適合用在dialog
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(375.dp)
                .padding(16.dp),
            // 形狀設定為圓角矩形
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painter,
                    contentDescription = contentDescription,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.height(180.dp)
                )
                Text(
                    text = "Are you sure you want to delete this attraction data?",
                    modifier = Modifier.padding(8.dp),
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    // 設定取消按鈕
                    TextButton(
                        onClick = { onDismissRequest() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Cancel")
                    }
                    // 設定確定按鈕
                    TextButton(
                        onClick = { onConfirmation() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Confirm")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CustomDialogDemoTheme {
        Main()
    }
}