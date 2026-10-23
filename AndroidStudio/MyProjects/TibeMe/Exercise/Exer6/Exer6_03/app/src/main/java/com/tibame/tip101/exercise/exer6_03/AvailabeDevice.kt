package com.tibame.tip101.exercise.exer6_03

import androidx.annotation.StringRes
import androidx.compose.ui.res.stringResource

enum class AvailabeDevice(
    @StringRes val id: Int,
){
    Phones(id = R.string.phones),
    Computers(id = R.string.computers),
}