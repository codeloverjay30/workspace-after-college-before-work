package com.jay30.composableeffectsdemo

import androidx.annotation.StringRes

enum class MainScreenEnum(@StringRes val title: Int) {
    LaunchedEffectDemoScreen(title = R.string.launched_effect_demo_screen),
    DisposableEffectDemoScreen(title = R.string.disposable_effect_demo_screen),
    SideEffectDemoScreen(title = R.string.side_effect),
}