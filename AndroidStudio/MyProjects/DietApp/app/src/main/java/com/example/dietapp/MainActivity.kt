package com.example.dietapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.dietapp.resources.stringresources.StringResourcesSingleton
import com.example.dietapp.ui.theme.DietAppTheme
import com.example.dietapp.util.components.progressindicator.determinateprogressindicator.MyDeterminateProgressIndicatorTest

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DietAppTheme {
                StringResourcesSingleton.initResource()
                MyDeterminateProgressIndicatorTest.test2()
            }
        }
    }
}