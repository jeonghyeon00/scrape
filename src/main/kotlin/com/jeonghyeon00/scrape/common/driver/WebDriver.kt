package com.jeonghyeon00.scrape.common.driver

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

interface WebDriver {
    fun get(url: String)

    fun quit()

    fun findElement(by: By): WebElement?

    fun findElements(by: By): List<WebElement>

    fun getPageSource(): String
}
