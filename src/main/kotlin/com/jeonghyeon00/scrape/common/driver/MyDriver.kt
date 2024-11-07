package com.jeonghyeon00.scrape.common.driver

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.slf4j.LoggerFactory

class MyDriver: WebDriver {
    private val driver: ChromeDriver = ChromeDriver()

    private val logger = LoggerFactory.getLogger(this::class.java)

    init {
        logger.info("ChromeDriver initialized ${driver.sessionId}")
    }

    override fun get(url: String) {
        driver.get(url)
    }

    override fun quit() {
        logger.debug("Quitting driver")
        driver.quit()
    }

    override fun findElement(by: By): WebElement? {
        return driver.findElement(by)
    }

    override fun findElements(by: By): List<WebElement> {
        return driver.findElements(by) ?: emptyList()
    }

    override fun getPageSource(): String {
        return driver.pageSource
    }
}
