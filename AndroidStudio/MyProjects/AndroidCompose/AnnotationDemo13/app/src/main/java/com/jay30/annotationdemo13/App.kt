package com.jay30.annotationdemo13

import javax.inject.Inject

class App {
    @Inject
    lateinit var car: Car

    fun setup() {
        val engine = Engine()
        car = Car(engine)
        DaggerCarComponent.create().inject(this)
        car.run()
    }
}
