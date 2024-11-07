package com.jeonghyeon00.scrape.platform.holaworld.scraper

import com.jeonghyeon00.scrape.common.driver.MyDriver
import com.jeonghyeon00.scrape.common.driver.WebDriver
import com.jeonghyeon00.scrape.platform.holaworld.entity.HolaWorldStudy
import com.jeonghyeon00.scrape.platform.holaworld.service.HolaWorldService
import org.openqa.selenium.By
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.DisposableBean
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class HolaWorldScraper(
    private val holaWorldService: HolaWorldService
): DisposableBean {
    private val logger = LoggerFactory.getLogger(this::class.java)
    private lateinit var driver: WebDriver

    companion object {
        const val BASE_URL = "https://holaworld.io/"
        const val PAGES = 10
    }

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.DAYS)
    fun scrapeAndSave() {
        driver = MyDriver()
        driver.get(BASE_URL)
        repeat(PAGES) {
            holaWorldService.saveStudies(getHolaWorldStudies(driver))
            goNextPage(driver)
            Thread.sleep(1000)
        }
        driver.quit()
    }

    private fun goNextPage(driver: WebDriver) {
        driver.findElement(By.xpath("//*[@id=\"root\"]/main/div[2]/nav/ul/li[10]/button"))?.click()
    }

    private fun getHolaWorldStudies(driver: WebDriver): List<HolaWorldStudy> {
        val holaWorldStudies = mutableListOf<HolaWorldStudy>()
        val elements = driver.findElements(By.xpath("//*[@id=\"root\"]/main/ul/a"))
        for (element in elements) {
            val title = element.findElement(By.tagName("h1")).text
            val positionElements = element.findElements(By.className("studyItem_position__2sRRD"))
            val roles = positionElements.joinToString(", ") { it.text }
            val holaWorldStudyId = element.getAttribute("href").removePrefix("https://holaworld.io/study/")
            holaWorldStudies.add(HolaWorldStudy(title = title, roles = roles, holaWorldStudyId = holaWorldStudyId))
        }
        return holaWorldStudies
    }

    override fun destroy() {
        driver.quit()
    }
}
