package com.jay30.servlettestproject1

import java.net.HttpURLConnection
import java.net.URL

fun MainScreen2() {
    val loginRepository = LoginRepository()
    loginRepository.makeLoginRequest()
}

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}

class LoginRepository() {
    private val loginUrl = "https://example.com/login"

    fun makeLoginRequest(){
        val url = URL(loginUrl)
        (url.openConnection() as? HttpURLConnection)?.run {
            requestMethod = "POST"
            setRequestProperty("Content-Type", "application/json; utf-8")
            setRequestProperty("Accept", "application/json")
            doOutput = true
        }
    }
}
