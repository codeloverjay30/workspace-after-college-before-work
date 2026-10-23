package com.jay30.annotationdemo13

import android.util.Log

class DaggerCarComponent: CarComponent {
    val TAG = "tag_DaggerCarComponent"

    override fun inject(app: App) {
        Log.e(TAG,"The car component is injected with app:${app} ")
    }

    companion object {
        val TAG = "tag_DaggerCarComponent_CompanionObject"

        fun create(): DaggerCarComponent {
            Log.e(TAG,"Try to create a Dagger car component.")
            return DaggerCarComponent()
        }
    }
}