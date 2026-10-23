package com.ron.lifecycledemo

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ron.lifecycledemo.ui.theme.LifecycleDemoTheme

@Composable
fun Screen01(
    navController: NavHostController
) {
    val context = LocalContext.current
    var uid by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = uid,
            onValueChange = { uid = it },
            label = { Text(text = stringResource(id = R.string.uid)) },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            // 使用者未輸入或輸入空白會顯示錯誤訊息
            if (uid.isBlank()) {
                message = context.getString(R.string.uid_blank)
                return@Button
            }
            // 切換到指定頁並傳遞資料
            navController.navigate("${Screen.Screen02.name}/$uid")
        }) {
            Text(text = stringResource(id = R.string.next_button))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = message)
    }


    val tag = "tag_Screen01"
    // 第一次進入composition(initial composition)時執行
    LaunchedEffect(Unit) {
        Log.d(tag, "LaunchedEffect")
    }

    /* 每個composable都有lifecycle owner，可透過LocalLifeCycleOwner.current取得。
        可利用它來加入observer以監控畫面的生命週期 */
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        // 在DisposableEffect內可以配合composable生命週期以正確建立與移除LifecycleEventObserver物件
        val observer = LifecycleEventObserver { _, event ->
            // 因為監控的是LocalLifeCycleOwner.current，所以下列是composable畫面的相關的生命週期事件
            when (event) {
                Lifecycle.Event.ON_CREATE -> Log.d(tag, "ON_CREATE")
                Lifecycle.Event.ON_START -> Log.d(tag, "ON_START")
                Lifecycle.Event.ON_RESUME -> Log.d(tag, "ON_RESUME")
                Lifecycle.Event.ON_PAUSE -> Log.d(tag, "ON_PAUSE")
                Lifecycle.Event.ON_STOP -> Log.d(tag, "ON_STOP")
                Lifecycle.Event.ON_DESTROY -> Log.d(tag, "ON_DESTROY")
                Lifecycle.Event.ON_ANY -> Log.d(tag, "ON_ANY")
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        // 離開composition時執行，可執行釋放資源動作
        onDispose {
            Log.d(tag, "onDispose")
            // 移除observer以停止監聽生命週期變化
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Screen01Preview() {
    LifecycleDemoTheme {
        Screen01(rememberNavController())
    }
}
