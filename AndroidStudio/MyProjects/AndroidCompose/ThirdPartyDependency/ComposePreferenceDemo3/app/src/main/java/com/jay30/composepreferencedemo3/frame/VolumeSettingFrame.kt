package com.jay30.composepreferencedemo3.frame

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.jay30.composepreferencedemo3.MyTopAppBar
import com.jay30.composepreferencedemo3.R
import me.zhanghai.compose.preference.ProvidePreferenceLocals
import me.zhanghai.compose.preference.SliderPreference

@Composable
fun VolumeSettingFrame(
    navController: NavHostController
) {
    val context = LocalContext.current

    var value by remember { mutableFloatStateOf(0f) }
    var sliderValue by remember { mutableFloatStateOf(0f) }

    Scaffold(
        topBar = {
            MyTopAppBar(
                title = {
                    Text(stringResource(R.string.volume_topappbar_title))
                },
                onClick = {
                    navController.popBackStack()
                }
            )
        }
    ) { innerPadding ->
        ProvidePreferenceLocals {
            // Other composables wrapping the LazyColumn ...
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                item {
                    SliderPreference(
                        title = { Text(text = context.getString(R.string.volume_topappbar_title)) },
                        icon = {
                            Icon(
                                painter = painterResource(R.drawable.baseline_volume_up_24),
                                contentDescription = null,
                            )
                        },
                        summary = { Text(text = "${value} and ${sliderValue} between range 0 to 1") },
                        value = value,
                        onValueChange = { value = it },
                        sliderValue = sliderValue,
                        onSliderValueChange = { sliderValue = it },
                    )
                }
            }
        }
    }
}