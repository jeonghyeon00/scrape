package com.jeonghyeon00.scrape.service

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.chrome.ChromeDriver
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.DisposableBean
import org.springframework.stereotype.Service

@Service
class SeleniumService : DisposableBean {
    private val logger = LoggerFactory.getLogger(this::class.java)

    private val driver = ChromeDriver()

    init {
        WebDriverManager.chromedriver().setup()
    }

    override fun destroy() {
        logger.info("SeleniumService destroy")
        driver.quit()
    }
}
