package com.jeonghyeon00.scrape.platform.holaworld.entity

import com.jeonghyeon00.scrape.common.entity.BaseTimeEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "hola_world_study")
class HolaWorldStudy(
    @Column(name = "title")
    val title: String,

    @Column(name = "roles")
    val roles: String,

    @Column(name = "hola_world_study_id", unique = true)
    val holaWorldStudyId: String,

    @Column(name = "technologies")
    val technologies: String
): BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    fun getUrl(): String {
        return "https://holaworld.io/study/$holaWorldStudyId"
    }
}
