package com.tibame.tip101.exercise.exer6_03

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController

@Composable
fun DeviceScreen(){
    var tabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf(
        stringResource(id = AvailabeDevice.Phones.id),
        stringResource(id = AvailabeDevice.Computers.id)
    )

    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            when (tabIndex) {
                0 -> PhonesScreen()
                1 -> ComputersScreen()
            }
        }

        TabRow(
            selectedTabIndex = tabIndex,
            containerColor = colorResource(id = R.color.teal_200)
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(text = title) },
                    selected = index == tabIndex,
                    onClick = { tabIndex = index },
                    selectedContentColor = colorResource(id = R.color.purple_700),
                    unselectedContentColor = Color.Gray,
                    icon = {
                        when (index) {
                            0 -> Icon(
                                painter = painterResource(id = R.drawable.phone),
                                contentDescription = stringResource(R.string.phones)
                            )

                            1 -> Icon(
                                painter = painterResource(id = R.drawable.computer),
                                contentDescription = stringResource(R.string.computers)
                            )
                        }
                    }
                )
            }
        }
    }
}
