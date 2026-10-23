package com.ron.webrequestdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MyViewModel: ViewModel() {
    private val _text = MutableStateFlow("")
    val text = _text.asStateFlow()

    fun getText() {
        /* 建立並啟動coroutine，coroutine scope會依附在ViewModel上；
            當ViewModel被清除，scope也會被取消 */
        viewModelScope.launch {
            // 網站說明：https://www.jsontest.com/
            val url = "http://ip.jsontest.com/"
            _text.value = getRemoteText(url)
        }
    }
}