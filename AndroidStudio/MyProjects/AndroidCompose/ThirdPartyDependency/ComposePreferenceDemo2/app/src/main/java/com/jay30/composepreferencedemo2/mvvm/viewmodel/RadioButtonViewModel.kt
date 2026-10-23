package com.jay30.composepreferencedemo2.mvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jay30.composepreferencedemo2.mvvm.repository.RadioButtonRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn

class RadioButtonViewModel: ViewModel() {
    private val repository = RadioButtonRepository
    val data = repository.dataFlow.asStateFlow()

    suspend fun fetchLatestData(): StateFlow<List<Boolean>> {
        val result = data.stateIn(viewModelScope)
        return result
    }
}