package com.ron.photopickerdemo

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.ron.photopickerdemo.ui.theme.PhotoPickerDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhotoPickerDemoTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    /* 呼叫rememberLauncherForActivityResult並搭配PickVisualMedia()
        以建立可以啟用photo picker的launcher物件。
        照片挑選完畢會執行onResult並傳來該照片的URI供後續處理 */
    val pickImageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri: Uri? ->
            selectedImageUri = uri
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                pickImageLauncher.launch(
                    PickVisualMediaRequest(
                        // 設定只能挑選圖片
                        ActivityResultContracts.PickVisualMedia.ImageOnly
                    )
                )
            }) {
            Text(text = stringResource(id = R.string.pick_image))
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 當照片被選取則URI不為null，就將該照片顯示
        selectedImageUri?.let { uri ->
            Image(
                painter = rememberAsyncImagePainter(uri),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PhotoPickerDemoTheme {
        Main()
    }
}