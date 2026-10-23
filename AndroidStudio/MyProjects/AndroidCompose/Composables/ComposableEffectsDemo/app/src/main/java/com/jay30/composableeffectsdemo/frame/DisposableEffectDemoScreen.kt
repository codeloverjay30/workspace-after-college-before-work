package com.jay30.composableeffectsdemo.frame

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jay30.composableeffectsdemo.MainScreenEnum
import com.jay30.composableeffectsdemo.MyTopAppBar
import com.jay30.composableeffectsdemo.R
import com.jay30.composableeffectsdemo.mvvm.repository.DataRepository
import com.jay30.composableeffectsdemo.mvvm.viewmodel.DataViewModel

@Composable
fun DisposableEffectDemoScreen(
    dataViewModel: DataViewModel,
    navController: NavHostController,
) {
    val dataVO by dataViewModel.data.collectAsState()

    DisposableEffect(Unit) {
        onDispose {
            dataVO?.let {
                it.title =
                    "title has been changed during execution of onDispose block in DisposableEffect(Unit) block."
                it.message =
                    "message has been changed during execution of onDispose block in DisposableEffect(Unit) block."
                DataRepository.setData(it)
            }
        }
    }

    Scaffold(topBar = {
        MyTopAppBar(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp), title = {
            Text(stringResource(MainScreenEnum.DisposableEffectDemoScreen.title))
        })
    }) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                Text("dataVO:${dataVO}")
            }

            item {
                Spacer(modifier = Modifier.height(10.dp))
            }

            item {
                Button(onClick = {
                    dataVO?.let {
                        it.title = "title has been changed due to button is clicked."
                        it.message = "message has been changed due to button is clicked."
                        DataRepository.setData(it)
                    }
                }
                ) {
                    Text(stringResource(R.string.change_title_and_message))
                }
            }
            item {
                Spacer(modifier = Modifier.height(10.dp))
            }

            item {
                Button(onClick = {
                    navController.navigate(MainScreenEnum.SideEffectDemoScreen.name)
                }) {
                    Text(stringResource(R.string.go_to_side_effect_demo_screen))
                }
            }

            item {
                Spacer(modifier = Modifier.height(10.dp))
            }

            item {
                Button(onClick = {
                    navController.navigate(MainScreenEnum.LaunchedEffectDemoScreen.name)
                }) {
                    Text(stringResource(R.string.go_to_launched_effect_demo_screen))
                }
            }
        }
    }
}