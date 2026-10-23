package com.example.exer4_02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.exer4_02.ui.components.slider.slider.MySliderTest
import com.example.exer4_02.ui.stringresources.StringResourcesSingleton
import com.example.exer4_02.ui.theme.Exer4_02Theme
import com.example.exer4_02.ui.theme.borrowbook.BorrowBookUITest

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Exer4_02Theme {
                StringResourcesSingleton.initResource()
                //MySliderTest.test1()
                BorrowBookUITest.test1()
                //MyDatePickerDialogTest.test1()
            }
        }
    }
}