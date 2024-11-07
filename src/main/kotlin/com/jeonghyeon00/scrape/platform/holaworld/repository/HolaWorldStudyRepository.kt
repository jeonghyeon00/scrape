package com.jeonghyeon00.scrape.platform.holaworld.repository

import com.jeonghyeon00.scrape.platform.holaworld.entity.HolaWorldStudy
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface HolaWorldStudyRepository : JpaRepository<HolaWorldStudy, Long> {
    fun findByHolaWorldStudyIdIn(holaWorldStudyIds: List<String>): List<HolaWorldStudy>
}
