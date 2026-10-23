package com.jay30.composepreferencedemo3.frame

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.jay30.composepreferencedemo3.component.RadioButtonGroupReference
import com.jay30.composepreferencedemo3.MyTopAppBar
import com.jay30.composepreferencedemo3.R
import me.zhanghai.compose.preference.ProvidePreferenceLocals

@Composable
fun CallVibrationSettingFrame(
    navController: NavHostController,
){
    val TAG = "tag_CallVibrationSettingFrame"

    val context = LocalContext.current

    val vibrationOptionResId: List<Int> = listOf(
        R.string.silent_vibration,
        R.string.short_vibration,
        R.string.meduim_vibration,
    )

    val titles: List<@Composable () -> Unit> = vibrationOptionResId.map{
        { Text(stringResource(it)) }
    }

    val summaries: List<@Composable() (() -> Unit)?>? = null

    val selecteds: MutableList<Boolean> = titles.map { false }.toMutableList()

    val onClicks: List<() -> Unit> = vibrationOptionResId.mapIndexed{ index, elem ->
        {
            if(selecteds.getOrNull(index)!= null){
                selecteds[index] = true
            }
        }
    }

    val modifier: Modifier = Modifier
    val enableds: List<Boolean> = vibrationOptionResId.map{true}
    val widgetContainer: @Composable() (() -> Unit)? = null

    Scaffold(
        topBar = {
            MyTopAppBar(
                title = {
                    Text(stringResource(R.string.call_vibration_topappbar_title))
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
            ) {
                item {
                    RadioButtonGroupReference(
                        titles = titles,
                        summaries = summaries,
                        onClicks = onClicks,
                        selecteds = selecteds,
                        modifier = modifier,
                        widgetContainer = widgetContainer,
                    )
                }
            }
        }
    }
}