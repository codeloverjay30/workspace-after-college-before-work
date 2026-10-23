package com.ron.composabledemo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ron.composabledemo.ui.theme.ComposableDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposableDemoTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    val tag = "tag_Main"
    // initial composition與recomposition時都會導致Composable函式被呼叫
    Log.d(tag, "Main()")

    var count1 = 0
    // 建立狀態變數(型別為State<T>或其子型)需要搭配remember方能在recomposition時儲存值的變化
    var count2 by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = count1.toString(),
            fontSize = 26.sp
        )
        // 因為count1不是狀態變數，值改變也不會導致Text重刷，所以依然顯示0
        // 但count1值確實有改變，開啟Logcat可看見count1的值
        Button(onClick = {
            count1++
            Log.d(tag, "count1 = $count1")
        }) {
            Text(text = "count1++")
        }
        Divider()
        Text(
            text = count2.toString(),
            color = Color.Companion.Blue,
            fontSize = 26.sp
        )
        // count2是狀態變數，值改變會recomposition而導致Main()函式重新被執行；
        // 因為搭配remember可以儲存值的變化，所以count2不會被重置；但count1會被重置(再點擊count1++按鈕並觀察值的變化)
        Button(onClick = {
            count2++
            Log.d(tag, "count2 = $count2")
        }) {
            Text(text = "count2++")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposableDemoTheme {
        Main()
    }
}