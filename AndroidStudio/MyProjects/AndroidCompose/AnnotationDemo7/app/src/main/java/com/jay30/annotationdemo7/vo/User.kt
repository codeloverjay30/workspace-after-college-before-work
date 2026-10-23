package com.jay30.annotationdemo7.vo

data class Animal(
    val name:String
){
    companion object{
        // tell JVM that the method is Java-style static
        @JvmStatic fun eat(food:String):String{
            return "An animal eats ${food}"
        }
    }
}
