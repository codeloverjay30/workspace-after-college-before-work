package com.ron.ex06_3

import androidx.annotation.StringRes

enum class Screen(@StringRes val title: Int) {
    Login(title = R.string.login),
    Products(title = R.string.products),
    Phones(title = R.string.phones),
    Computers(title = R.string.computers)
}