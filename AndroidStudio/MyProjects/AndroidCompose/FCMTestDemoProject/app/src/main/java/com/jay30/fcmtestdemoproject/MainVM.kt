package com.jay30.fcmtestdemoproject

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainVM : ViewModel() {
    // 來自於repository儲存的資料
    private val repository = MyRepository
    val dataFlow: StateFlow<String> = repository.dataFlow

    private val _tokenFlow = MutableStateFlow("")
    val tokenFlow = _tokenFlow.asStateFlow() // 外部僅可讀

    // 無需使用者操作即可取得token並送到server
    init {
        retrieveToken()
    }

    // 取得token並送到server
    private fun retrieveToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                task.result?.let { token ->
                    _tokenFlow.value = token
                    // 複製token到Firebase的Cloud Messaging測試區
                    Log.d("retrieveToken()", "token: $token")
                }
            }
        }
    }
}

