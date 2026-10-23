package com.jay30.annotationdemo11.mvvm.repository

import androidx.compose.runtime.ReadOnlyComposable
import com.jay30.annotationdemo11.mvvm.vo.UserVO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

object UserRepository {
    private val _dataflow = MutableStateFlow(fetchData())
    val dataflow:MutableStateFlow<UserVO>
        @ReadOnlyComposable
        get() = _dataflow

    fun fetchData():UserVO{
        return UserVO(2)
    }

    fun setData(newData: UserVO){
        _dataflow.update { newData }
    }
}