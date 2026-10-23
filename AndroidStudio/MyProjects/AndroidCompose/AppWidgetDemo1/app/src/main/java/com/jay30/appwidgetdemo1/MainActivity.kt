package com.jay30.appwidgetdemo1

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jay30.appwidgetdemo1.ui.theme.AppWidgetDemo1Theme

class MainActivity : ComponentActivity() {
    val TAG = "tag_MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val remoteView = findViewById<View>(R.id.linearlayout1)
        setContent {
            AppWidgetDemo1Theme {
                if(remoteView!=null){
                    Log.e(TAG,"remoteView.id:${remoteView.id}")
                }else{
                    Log.e(TAG,"remoteView is null.")
                }
            }
        }
    }
}