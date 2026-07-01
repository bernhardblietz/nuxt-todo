package com.bernhardblietz.nuxt_todo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NuxtTodoApplication

fun main(args: Array<String>) {
	runApplication<NuxtTodoApplication>(*args)
}
