package com.tibame.tip101.group02.finalproject.healthhelper.frame

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.tibame.tip101.group02.finalproject.healthhelper.components.bar.appbar.topappbar.DietAppTopBar
import com.tibame.tip101.group02.finalproject.healthhelper.components.textfield.outlinedtextfield.SearchTextField
import com.tibame.tip101.group02.finalproject.healthhelper.consts.stringresourcesingleton.StringResourceSingleton

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SearchHintFrame(
    navController: NavHostController,
){
    Scaffold(
        topBar = {
            DietAppTopBar(
                navController = navController,
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                Text(
                    text = StringResourceSingleton.SEARCH_HINT_TITLE,
                    fontSize = 36.sp,
                )

                Text(
                    text = StringResourceSingleton.SEARCH_ALGORITHM_NAME,
                    fontSize = 26.sp,
                )

                Text(
                    text = StringResourceSingleton.SEARCH_ALGORITHM_INTRO,
                    fontSize = 18.sp,
                )
            }
        }
    )
}