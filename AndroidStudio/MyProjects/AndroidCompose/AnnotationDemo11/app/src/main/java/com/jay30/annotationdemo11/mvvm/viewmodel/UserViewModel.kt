package com.jay30.annotationdemo11.mvvm.viewmodel

import androidx.lifecycle.ViewModel
import com.jay30.annotationdemo11.mvvm.repository.UserRepository
import kotlinx.coroutines.flow.asStateFlow

class UserViewModel:ViewModel() {
    private val repository = UserRepository
    val data = repository.dataflow.asStateFlow()
}