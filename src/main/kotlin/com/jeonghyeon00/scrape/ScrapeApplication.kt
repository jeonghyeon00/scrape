package com.jeonghyeon00.scrape

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ScrapeApplication

fun main(args: Array<String>) {
    runApplication<ScrapeApplication>(*args)
}
