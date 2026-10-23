package com.jay30.composepreferencedemo3.frame

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import me.zhanghai.compose.preference.ListPreference
import me.zhanghai.compose.preference.ProvidePreferenceLocals

@Composable
fun RingtoneSettingFrame(
    navController: NavHostController,
){
    val context = LocalContext.current

    val options = listOf("Happy","Mad")
    var selectedOptions by remember { mutableStateOf(options[0]) }

    Scaffold(
        topBar = {
            MyTopAppBar(
                title = {
                    Text(stringResource(R.string.ringtone_topappbar_title))
                },
                onClick = {
                    navController.popBackStack()
                }
            )
        }
    ) { innerPadding ->
        ProvidePreferenceLocals {
            LazyColumn(modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)) {
                item{
                ListPreference(
                    value = selectedOptions,
                    onValueChange = { selectedOptions = it },
                    values = options,
                    title = { Text(stringResource(R.string.mood_question))},
                    summary = { Text(text = selectedOptions.toString())}
                )
                }
            }
        }
    }
}