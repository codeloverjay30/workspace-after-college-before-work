package com.jay30.composableeffectsdemo.mvvm.viewmodel

import androidx.lifecycle.ViewModel
import com.jay30.composableeffectsdemo.mvvm.repository.DataRepository

class DataViewModel: ViewModel() {
    private val repository = DataRepository
    val data = repository.dataFlow
}