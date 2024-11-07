package com.jeonghyeon00.scrape.platform.holaworld.service

import com.jeonghyeon00.scrape.platform.holaworld.entity.HolaWorldStudy
import com.jeonghyeon00.scrape.platform.holaworld.repository.HolaWorldStudyRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class HolaWorldService(
    private val holaWorldStudyRepository: HolaWorldStudyRepository
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun saveStudies(studies: List<HolaWorldStudy>) {
        if (studies.isEmpty()) return

        val studyIds = studies.map { it.holaWorldStudyId }
        val existingStudies = holaWorldStudyRepository.findByHolaWorldStudyIdIn(studyIds)
        val existingStudyIds = existingStudies.map { it.holaWorldStudyId }.toSet()

        val newStudies = studies.filter { it.holaWorldStudyId !in existingStudyIds }

        if (newStudies.isNotEmpty()) {
            holaWorldStudyRepository.saveAll(newStudies)
            logger.info("Saved ${newStudies.size} new studies.")
        } else {
            logger.info("No new studies to save.")
        }
    }
}
