package com.example.exer4_02.ui.stringresources

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.exer4_02.R

object StringResourcesSingleton {
    var OK : String = ""
    var CANCEL : String = ""

    @Composable
    fun initResource() {
        OK = stringResource(R.string.ok)
        CANCEL = stringResource(R.string.cancel)
    }
}