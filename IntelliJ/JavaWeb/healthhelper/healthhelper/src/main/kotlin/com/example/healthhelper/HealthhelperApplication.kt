package com.example.healthhelper

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HealthhelperApplication

fun main(args: Array<String>) {
	runApplication<HealthhelperApplication>(*args)
}
