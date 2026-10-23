package com.tibame.tip101.group02.finalproject.healthhelper.frame

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.tibame.tip101.group02.finalproject.healthhelper.components.dialog.alertdialog.MyAlertDialog
import com.tibame.tip101.group02.finalproject.healthhelper.components.bar.appbar.topappbar.DietAppTopBar
import com.tibame.tip101.group02.finalproject.healthhelper.components.textfield.outlinedtextfield.DateTextField
import com.tibame.tip101.group02.finalproject.healthhelper.components.textfield.outlinedtextfield.FoodTextField
import com.tibame.tip101.group02.finalproject.healthhelper.components.textfield.outlinedtextfield.NameTextField
import com.tibame.tip101.group02.finalproject.healthhelper.components.textfield.outlinedtextfield.TimeTextField
import com.tibame.tip101.group02.finalproject.healthhelper.consts.stringresourcesingleton.StringResourceSingleton
import com.tibame.tip101.group02.finalproject.healthhelper.enumclass.ScreenEnum
import com.tibame.tip101.group02.finalproject.healthhelper.viewmodel.DiaryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewDietDiaryItemFrame(
    navController: NavHostController
) {
    var shouldPopUpAlertDialog by remember { mutableStateOf(false) }

    val name = remember { mutableStateOf("") }
    val dateText = remember { mutableStateOf("") }
    val timeText = remember { mutableStateOf("") }
    val foodText = remember { mutableStateOf("") }

    if(shouldPopUpAlertDialog){
        MyAlertDialog.popUp(
            onConfirmation = { shouldPopUpAlertDialog = false},
            onDismissRequest = { shouldPopUpAlertDialog = false },
            dialogTitle = StringResourceSingleton.ERROR_INVALID_INPUT,
            dialogText = StringResourceSingleton.INVALID_TEXTFIELD_VALUE_FOR_NAME_AND_DATE_AND_TIME ,
        )
    }
    Scaffold(
        topBar = {
            DietAppTopBar(
                navController = navController,
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (
                        name.value.isNotBlank() &&
                        foodText.value.isNotBlank() &&
                        dateText.value.isNotBlank() &&
                        timeText.value.isNotBlank()
                    ) {
                        DiaryViewModel.addDiary(
                            name = name.value,
                            foodName = foodText.value,
                            date = dateText.value,
                            time = timeText.value,
                        )
                        navController.navigate(ScreenEnum.DietDiaryMainFrame.name)
                        return@FloatingActionButton
                    }
                    shouldPopUpAlertDialog = true
                    return@FloatingActionButton
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = StringResourceSingleton.ADD_NEW_ITEM_ICON,
                )
            }
        },
        content = { innerPadding ->
            val textFieldWidth = 300
            val spacerHeight = 20
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                NameTextField(
                    modifier = Modifier.width(textFieldWidth.dp),
                    mutableStateValue = name,
                    onValueChange = {name.value = it},
                )
                Spacer(modifier = Modifier
                    .height(spacerHeight.dp)
                    .fillMaxWidth())
                FoodTextField(
                    modifier = Modifier
                        .width(textFieldWidth.dp)
                        .fillMaxWidth(),
                    mutableStateValue = foodText,
                    onValueChange = {foodText.value = it },
                )
                Spacer(modifier = Modifier
                    .height(spacerHeight.dp)
                    .fillMaxWidth())
                DateTextField(
                    modifier = Modifier.width(textFieldWidth.dp),
                    mutableStateValue = dateText,
                    onValueChange = {dateText.value = it},
                )
                Spacer(modifier = Modifier
                    .height(spacerHeight.dp)
                    .fillMaxWidth())
                TimeTextField(
                    modifier = Modifier
                        .width(textFieldWidth.dp)
                        .fillMaxWidth(),
                    mutableStateValue = timeText,
                    onValueChange = {timeText.value = it},
                )
            }
        }
    )
}