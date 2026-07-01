package com.bernhardblietz.nuxt_todo

import java.util.concurrent.atomic.AtomicLong

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

private const val template = "Hello, "

@RestController
class GreetingController {

  private val counter = AtomicLong()

  @GetMapping("/greeting")
  fun greeting(@RequestParam name: String = "World") =
    Greeting(counter.incrementAndGet(), template + name)

}