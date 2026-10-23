package com.jay30.composepreferencedemo2.frame

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.jay30.composepreferencedemo2.MyTopAppBar
import com.jay30.composepreferencedemo2.R
import com.jay30.composepreferencedemo2.component.RadioButtonGroupReference
import com.jay30.composepreferencedemo2.extension.flow.flowWithLifecycleStateInAndCollectAsState
import com.jay30.composepreferencedemo2.mvvm.repository.RadioButtonRepository
import com.jay30.composepreferencedemo2.mvvm.viewmodel.RadioButtonViewModel
import com.jay30.composepreferencedemo2.mvvm.vo.RadioButtonVO
import kotlinx.coroutines.launch
import me.zhanghai.compose.preference.ProvidePreferenceLocals

@Composable
fun CallVibrationSettingFrame(
    navController: NavHostController,
    radioButtonViewModel: RadioButtonViewModel = viewModel(),
){
    val TAG = "tag_CallVibrationSettingFrame"

    val context = LocalContext.current

    val rememberScope = rememberCoroutineScope()

    var anyOptionIsClicked by remember { mutableStateOf(false) }

    val vibrationOptionResId: List<Int> = listOf(
        R.string.silent_vibration,
        R.string.short_vibration,
        R.string.meduim_vibration,
    )

    val titles: List<@Composable () -> Unit> = vibrationOptionResId.map{
        { Text(stringResource(it)) }
    }

    val summaries: List<@Composable() (() -> Unit)?>? = null
    val newList = titles.map { false }
    Log.e(TAG,"newList:${newList}")
    RadioButtonRepository.setData(newList)

    //val selecteds = radioButtonViewModel.data.collectAsState()
    //val selecteds = radioButtonViewModel.data.flowWithLifecycleStateInAndCollectAsState(rememberScope)
    var selecteds = radioButtonViewModel.data.flowWithLifecycleStateInAndCollectAsState(rememberScope)


    Log.e(TAG,"-".repeat(50))
    Log.e(TAG,"selecteds.value:${selecteds.value}")
    Log.e(TAG,"-".repeat(50))

    val onClicks: List<() -> Unit> = vibrationOptionResId.mapIndexed{ index, elem ->
        {
            anyOptionIsClicked = true
            Log.e(TAG,"~".repeat(50))
            Log.e(TAG,"onClicks variable was called, index:${index}")
            val newList = selecteds.value?.map { false }?.toMutableList()
            if(newList?.getOrNull(index)!= null){
                Log.e(TAG,"newList.getOrNull($index)!= null is true")
                newList[index] = true
                Log.e(TAG,"newList:$newList")
                RadioButtonRepository.setData(newList.toList())
            }
            Log.e(TAG,"onClicks variable was finished to called, index:${index}")
            Log.e(TAG,"~".repeat(50))
        }
    }

    if(anyOptionIsClicked){
        Log.e(TAG,"*".repeat(50))
        Log.e(TAG,"anyOptionIsClicked is true")
        selecteds = radioButtonViewModel.data.flowWithLifecycleStateInAndCollectAsState(rememberScope)
        anyOptionIsClicked = false
        Log.e(TAG,"*".repeat(50))
    }

    Log.e(TAG,"!".repeat(50))
    Log.e(TAG,"selecteds.value:${selecteds.value}")
    Log.e(TAG,"!".repeat(50))

    val modifier: Modifier = Modifier
    val enableds: List<Boolean> = vibrationOptionResId.map{true}
    val widgetContainer: @Composable() (() -> Unit)? = null

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
                    Log.e(TAG,"RadioButtonGroupReference function is ready to be called.")
                    Log.e(TAG,"selecteds.value:${selecteds.value}")
                    RadioButtonGroupReference(
                        titles = titles,
                        summaries = summaries,
                        onClicks = onClicks,
                        selecteds = selecteds.value,
                        modifier = modifier,
                        widgetContainer = widgetContainer,
                    )
                }
            }
        }
    }
}