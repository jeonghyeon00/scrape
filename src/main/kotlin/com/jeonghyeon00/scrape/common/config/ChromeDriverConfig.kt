package com.jeonghyeon00.scrape.common.config

import io.github.bonigarcia.wdm.WebDriverManager
import org.springframework.context.annotation.Configuration

@Configuration
class ChromeDriverConfig {
    init {
        WebDriverManager.chromedriver().setup()
    }
}
