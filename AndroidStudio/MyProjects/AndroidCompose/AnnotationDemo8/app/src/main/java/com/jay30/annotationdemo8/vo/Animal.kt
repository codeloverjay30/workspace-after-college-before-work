package com.jay30.annotationdemo8.vo

// This following line will cause compiler error as `@MustBeDocumented` can be marked on definition of `annotation class`.
// @MustBeDocumented
class Animal(
    val name:String
){
    fun eat(food:String):String{
        return "${name} eats ${food}"
    }
}