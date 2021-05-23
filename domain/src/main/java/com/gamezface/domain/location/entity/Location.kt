package com.gamezface.domain.location.entity

data class Location(
    val id: Long,
    val name: String,
    val review: Float,
    val type: String,
    val about: String?,
    val phone: String?,
    val address: String?,
    val schedule: Schedules?,
    var imageUrl: String? = null
)