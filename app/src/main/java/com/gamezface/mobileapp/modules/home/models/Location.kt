package com.gamezface.mobileapp.modules.home.models

data class Location(
    val id: Long,
    val name: String,
    val review: Float,
    val type: String,
    val about: String,
    val phone: String,
    val adress: String,
    val schedule: Any,
    var imageUrl: String?
)