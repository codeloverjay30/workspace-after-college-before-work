package com.jay30.composableeffectsdemo.mvvm.repository

import com.jay30.composableeffectsdemo.mvvm.vo.DataVO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object DataRepository{

    private val _dataFlow= MutableStateFlow(DataVO())
    val dataFlow : StateFlow<DataVO>
        get() = _dataFlow.asStateFlow()

    fun setData(newDataVO: DataVO){
        _dataFlow.update { newDataVO }
    }
}