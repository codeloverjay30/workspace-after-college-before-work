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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jay30.composableeffectsdemo.MainScreenEnum
import com.jay30.composableeffectsdemo.MyTopAppBar
import com.jay30.composableeffectsdemo.R
import com.jay30.composableeffectsdemo.mvvm.viewmodel.DataViewModel

@Composable
fun LaunchedEffectDemoScreen(
    dataViewModel: DataViewModel,
    navController: NavHostController,
) {
    val dataVO by dataViewModel.data.collectAsState()

    var willBeChangedOnceRecompositionVar1 by remember { mutableIntStateOf(0) }
    var alwaysBeChangedVar1 by remember { mutableIntStateOf(0) }
    var counter by remember { mutableIntStateOf(0) }
    var counterMessage by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        willBeChangedOnceRecompositionVar1++
    }

    LaunchedEffect(counter) {
        counterMessage = "counter:${counter}"
    }

    LaunchedEffect(true) {
        alwaysBeChangedVar1 ++
    }

    Scaffold(
        topBar = {
            MyTopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                title = {
                    Text(stringResource(MainScreenEnum.LaunchedEffectDemoScreen.title))
                }
            )
        },
    ) { innerPadding ->
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
                Text("willBeChangedOnceRecompositionVar1:${willBeChangedOnceRecompositionVar1}")
            }

            item {
                Spacer(modifier = Modifier.height(10.dp))
            }

            item {
                Text("alwaysBeChangedVar1:${alwaysBeChangedVar1}")
            }

            item {
                Text("${counter}")
            }

            item {
                Spacer(modifier = Modifier.height(10.dp))
            }

            item {
                Text(counterMessage)
            }

            item {
                Spacer(modifier = Modifier.height(10.dp))
            }

            item {
                Button(onClick = {
                    counter ++
                }) {
                    Text(stringResource(R.string.increment))
                }
            }

            item {
                Button(onClick = {
                    navController.navigate(MainScreenEnum.DisposableEffectDemoScreen.name)
                }) {
                    Text(stringResource(R.string.go_to_disposable_effect_demo_screen))
                }
            }

            item {
                Button(onClick = {
                    navController.navigate(MainScreenEnum.SideEffectDemoScreen.name)
                }) {
                    Text(stringResource(R.string.go_to_side_effect_demo_screen))
                }
            }
        }
    }
}