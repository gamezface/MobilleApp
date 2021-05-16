package com.gamezface.mobileapp.modules.home.models

data class Location constructor(
    val id: Long,
    val name: String,
    val review: Float,
    val type: String,
    val about: String,
    val phone: String,
    val address: String,
    val schedule: Schedules,
    var imageUrl: String?
)