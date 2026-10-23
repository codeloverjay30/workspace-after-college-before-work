package com.jay30.composepreferencedemo3.frame

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.jay30.composepreferencedemo3.MyTopAppBar
import com.jay30.composepreferencedemo3.R
import com.jay30.composepreferencedemo3.enumclass.MainScreenEnum
import me.zhanghai.compose.preference.MultiSelectListPreference
import me.zhanghai.compose.preference.Preference
import me.zhanghai.compose.preference.ProvidePreferenceLocals

@Composable
fun AppSettingFrame(
    navController: NavHostController
){
    val context = LocalContext.current

    val options = listOf("Ark ReCode","Candy crush saga")
    var selectedOptions by remember { mutableStateOf(setOf<String>()) }

    Scaffold(
        topBar = {
            MyTopAppBar(
                title = {
                    Text(stringResource(R.string.connections_topappbar_title))
                },
                onClick = {
                    navController.popBackStack()
                }
            )
        }
    ) { innerPadding ->
        ProvidePreferenceLocals {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ){
                item {
                    MultiSelectListPreference(
                        value = selectedOptions,
                        onValueChange = { selectedOptions = it },
                        values = options,
                        title = { Text(text = context.getString(R.string.app_topappbar_title)) },
                        summary = { Text(text = selectedOptions.toString()) }
                    )
                }
            }
        }
    }
}