package com.jay30.composepreferencedemo2.mvvm.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

object RadioButtonRepository {
    private val _dataFlow = MutableStateFlow(fetchData())
    val dataFlow : MutableStateFlow<List<Boolean>>
        get() = _dataFlow

    fun setData(datas:List<Boolean>){
        _dataFlow.update { datas }
    }

    private fun fetchData():List<Boolean>{
        return listOf<Boolean>()
    }
}