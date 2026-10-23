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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.tibame.tip101.group02.finalproject.healthhelper.components.bar.appbar.topappbar.DietAppTopBar
import com.tibame.tip101.group02.finalproject.healthhelper.components.card.DietDiaryCards
import com.tibame.tip101.group02.finalproject.healthhelper.components.textfield.outlinedtextfield.SearchTextField
import com.tibame.tip101.group02.finalproject.healthhelper.consts.stringresourcesingleton.StringResourceSingleton
import com.tibame.tip101.group02.finalproject.healthhelper.enumclass.ScreenEnum
import com.tibame.tip101.group02.finalproject.healthhelper.viewmodel.DiaryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DietDiaryMainFrame(
    navController: NavHostController,
){
    Scaffold(
        topBar = {
            DietAppTopBar(
                navController = navController,
            )
        },
        floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        navController.navigate(ScreenEnum.AddNewDietDiaryItemFrame.name)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = StringResourceSingleton.ADD_NEW_ITEM_ICON,
                    )
            }
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
            ){
                SearchTextField(
                    navController = navController,
                    modifier = Modifier.fillMaxWidth(),
                )

                DietDiaryCards(
                    cards = DiaryViewModel.diaries,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    )
}