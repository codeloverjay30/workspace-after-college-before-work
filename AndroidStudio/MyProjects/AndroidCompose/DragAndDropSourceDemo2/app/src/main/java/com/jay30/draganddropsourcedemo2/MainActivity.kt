package com.jay30.draganddropsourcedemo2

import android.os.Bundle
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
import com.jay30.draganddropsourcedemo2.ui.theme.DragAndDropSourceDemo2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DragAndDropSourceDemo2Theme {
                DragAndDropSourceDemo2Frame(
                    context = androidx.compose.ui.platform.LocalContext.current
                )
            }
        }
    }
}
