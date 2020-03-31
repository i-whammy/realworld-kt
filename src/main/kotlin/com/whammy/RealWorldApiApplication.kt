package com.whammy

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class RealWorldApiApplication

fun main(args: Array<String>) {
    SpringApplication.run(RealWorldApiApplication::class.java)
}