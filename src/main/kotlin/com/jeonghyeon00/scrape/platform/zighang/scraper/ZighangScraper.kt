package com.jeonghyeon00.scrape.platform.zighang.scraper

import com.jeonghyeon00.scrape.common.driver.MyDriver
import com.jeonghyeon00.scrape.common.driver.WebDriver
import org.openqa.selenium.By
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.DisposableBean
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class ZighangScraper : DisposableBean {
    private val logger = LoggerFactory.getLogger(this::class.java)
    private lateinit var driver: WebDriver

    companion object {
        private const val BASE_URL = "https://zighang.com/it"
    }

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.DAYS)
    fun scrape() {
        driver = MyDriver()
        driver.get(BASE_URL)
        Thread.sleep(2000)

        repeat(10) {
            scrollDown()
        }

        val container = driver.findElement(By.xpath("/html/body/main/div[3]/div/div")) ?: return
        val anchorElements = container.findElements(By.tagName("a"))
        for (element in anchorElements) {
            val url = element.getAttribute("href")
            val splitText = element.text.split("\n")
            val title = splitText[2]
            val company = splitText[3]
            val location = splitText[4]
            logger.info("title: $title, company: $company, location: $location, url: $url")
        }
        driver.quit()
    }

    private fun scrollDown() {
        driver.executeScript("window.scrollTo(0, document.body.scrollHeight);")
        Thread.sleep(1500)
    }

    override fun destroy() {
        driver.quit()
    }
}
