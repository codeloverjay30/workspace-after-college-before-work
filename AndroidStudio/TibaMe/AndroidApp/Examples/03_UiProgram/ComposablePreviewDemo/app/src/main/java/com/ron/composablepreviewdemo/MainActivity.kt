package com.ron.composablepreviewdemo

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ron.composablepreviewdemo.ui.theme.ComposablePreviewDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposablePreviewDemoTheme {
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
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Text(
            text = stringResource(R.string.hello),
            color = colorResource(R.color.purple_200)
        )
        Image(
            painter = painterResource(R.drawable.image),
            contentDescription = "image",
            modifier = Modifier
                .size(200.dp)
        )
    }
}

// 尺寸：預設為自動包覆內容大小，也可使用 heightDp 和 widthDp 參數設定寬高
@Preview(name = "showBackground", showBackground = true)
// 如果需要在預覽畫面中顯示狀態和動作列，使用 showSystemUi 參數
@Preview(name = "showSystemUi", showSystemUi = true)
// 顯示dark mode需使用 uiMode 參數
@Preview(name = "dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreview() {
    ComposablePreviewDemoTheme {
        Main()
    }
}