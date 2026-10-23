package com.tibame.tip101.exercise.exer5_01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tibame.tip101.exercise.exer5_01.stringresources.StringResourcesSingleton
import com.tibame.tip101.exercise.exer5_01.ui.mainui.MainUI
import com.tibame.tip101.exercise.exer5_01.ui.theme.Exer5_01Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Exer5_01Theme {
                StringResourcesSingleton.initResource()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Main(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Main(modifier: Modifier = Modifier){
    MainUI.popUp(modifier)
}

@Preview(showBackground = true)
@Composable
fun MainPreview(){
    Main()
}