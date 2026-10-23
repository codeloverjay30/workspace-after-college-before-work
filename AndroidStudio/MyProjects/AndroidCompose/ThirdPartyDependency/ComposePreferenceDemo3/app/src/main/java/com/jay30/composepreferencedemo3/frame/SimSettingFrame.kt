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
import me.zhanghai.compose.preference.CheckboxPreference
import me.zhanghai.compose.preference.ProvidePreferenceLocals

@Composable
fun SimSettingFrame(
    navController: NavHostController,
){
    val context = LocalContext.current
    var checked by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            MyTopAppBar(
                title = {
                    Text(stringResource(R.string.sim_topappbar_title))
                },
                onClick = {
                    navController.popBackStack()
                }
            )
        }
    ) { innerPadding ->
        ProvidePreferenceLocals {
            // Other composables wrapping the LazyColumn ...
            LazyColumn(modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)) {
                item {
                    CheckboxPreference(
                        value = checked,
                        onValueChange = {checked = it},
                        title = { Text(text = context.getString(R.string.sim_topappbar_title)) },
                        summary = { Text(text = if (checked) "On" else "Off") }
                    )
                }
            }
        }
    }
}