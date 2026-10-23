package com.jay30.composablenavigationdemo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController


@Composable
fun MainFrame(
    navController: NavHostController,
){
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        item{
            Text(stringResource(R.string.welcome_message, stringResource(R.string.main_frame)))
            Button(onClick = {
                navController.navigate(MainScreenEnum.PROFILE_FRAME.name)
            }) {
                Text(stringResource(R.string.jump_to, stringResource(MainScreenEnum.PROFILE_FRAME.title)))
            }
        }
    }
}