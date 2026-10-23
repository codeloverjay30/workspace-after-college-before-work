package com.jay30.annotationdemo13

import dagger.Component

@Component(modules = [EngineModule::class])
interface CarComponent {
    fun inject(app: App)
}