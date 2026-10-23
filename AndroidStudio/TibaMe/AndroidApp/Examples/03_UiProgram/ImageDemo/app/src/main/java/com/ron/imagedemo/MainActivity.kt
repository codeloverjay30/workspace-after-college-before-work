package com.ron.imagedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ron.imagedemo.ui.theme.ImageDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImageDemoTheme {
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
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 內容縮放參看: https://developer.android.com/develop/ui/compose/graphics/images/customize?hl=zh-tw#content-scale
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Image(
                // 圖片來至res/drawable目錄
                painter = painterResource(id = R.drawable.myimage),
                // 設定文字描述主要用於無障礙服務
                contentDescription = "image",
                // 設定圖框尺寸與背景色
                modifier = Modifier
                    .size(150.dp)
                    .background(Color.Green),
                // contentScale 可設定圖片在圖框內的縮放方式，預設為Fit(等比例縮放)
                contentScale = ContentScale.Fit
            )
            Image(
                painter = painterResource(id = R.drawable.myimage),
                contentDescription = "image",
                modifier = Modifier
                    .size(150.dp)
                    .background(Color.Green),
                // FillBounds: 圖片填滿圖框，圖片與圖框尺寸比例不同時會發生變形
                contentScale = ContentScale.FillBounds
            )
        }

        // 裁剪形狀參看: https://developer.android.com/develop/ui/compose/graphics/images/customize?hl=zh-tw#clip-image-shape
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Image(
                painter = painterResource(id = R.drawable.myimage),
                contentDescription = "image",
                modifier = Modifier
                    .size(150.dp)
                    // 將圖片裁剪為圓形
                    .clip(CircleShape),
                // 根據可用空間置中裁剪圖片
                contentScale = ContentScale.Crop
            )
            Image(
                painter = painterResource(id = R.drawable.myimage),
                contentDescription = "image",
                modifier = Modifier
                    .size(150.dp)
                    // 將圖片裁剪為園角矩形
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        }

        // 加邊框參看: https://developer.android.com/develop/ui/compose/graphics/images/customize?hl=zh-tw#border-image
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Image(
                painter = painterResource(id = R.drawable.myimage),
                contentDescription = "image",
                modifier = Modifier
                    .size(150.dp)
                    // 建立圓形邊框
                    .border(BorderStroke(8.dp, Color(0xFFFF9800)), CircleShape)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Image(
                painter = painterResource(id = R.drawable.myimage),
                contentDescription = "image",
                modifier = Modifier
                    .size(150.dp)
                    .border(
                        BorderStroke(
                            8.dp,
                            // 建立多色漸層，3點鐘方向順時鐘繪製顏色
                            Brush.sweepGradient(
                                listOf(
                                    Color.Red,
                                    Color.Yellow,
                                    Color.Green,
                                    Color.Blue,
                                    Color.Cyan,
                                    Color.Red
                                )
                            )
                        ), RoundedCornerShape(8.dp)
                    )
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        }

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            // 色彩濾鏡參看: https://developer.android.com/develop/ui/compose/graphics/images/customize?hl=zh-tw#color-filter
            Image(
                painter = painterResource(id = R.drawable.myimage),
                contentDescription = "image",
                modifier = Modifier.size(150.dp),
                contentScale = ContentScale.Crop,
                // 圖片套用黑白濾鏡，可以使用ColorMatrix，並將飽和度設定為0f
                colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) })
            )
            // 模糊處理參看: https://developer.android.com/develop/ui/compose/graphics/images/customize?hl=zh-tw#blur_an_image_composable
            Image(
                painter = painterResource(id = R.drawable.myimage),
                contentDescription = "image",
                modifier = Modifier
                    .size(150.dp)
                    // 圖片模糊處理，radiusX, radiusY分別指定水平和垂直的模糊半徑
                    // 加設edgeTreatment為Unbounded會將邊界一併模糊處理；不加則否
                    .blur(
                        radiusX = 3.dp,
                        radiusY = 3.dp,
                        edgeTreatment = BlurredEdgeTreatment.Unbounded
                    ),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ImageDemoTheme {
        Main()
    }
}