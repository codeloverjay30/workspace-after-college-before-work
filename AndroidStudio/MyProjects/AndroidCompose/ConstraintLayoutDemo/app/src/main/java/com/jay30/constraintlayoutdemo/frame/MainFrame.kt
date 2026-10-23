package com.jay30.constraintlayoutdemo.frame

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jay30.constraintlayoutdemo.R
import com.jay30.constraintlayoutdemo.screen.MainScreenEnum

@Composable
fun MainFrame(
    navController: NavHostController,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        item{
            Button(onClick = {
                navController.navigate(MainScreenEnum.CONSTRAINT_LAYOUT_DEMO_FRAME.name)
            }){
                Text(stringResource(MainScreenEnum.CONSTRAINT_LAYOUT_DEMO_FRAME.title))
            }

            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))

            Button(onClick = {
                navController.navigate(MainScreenEnum.DECOUPLED_CONSTRAINT_LAYOUT_DEMO_FRAME.name)
            }){
                Text(stringResource(MainScreenEnum.DECOUPLED_CONSTRAINT_LAYOUT_DEMO_FRAME.title))
            }

            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))

            Button(onClick = {
                navController.navigate(MainScreenEnum.CONSTRAINT_LAYOUT_GUIDELINES_DEMO_FRAME.name)
            }){
                Text(stringResource(MainScreenEnum.CONSTRAINT_LAYOUT_GUIDELINES_DEMO_FRAME.title))
            }
        }
    }
}