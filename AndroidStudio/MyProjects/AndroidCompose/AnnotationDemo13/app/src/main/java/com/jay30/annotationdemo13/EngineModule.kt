package com.jay30.annotationdemo13

import dagger.Module
import dagger.Provides

@Module
class EngineModule {
    @Provides
    fun provideEngine(): Engine = Engine()
}