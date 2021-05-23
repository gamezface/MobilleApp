package com.gamezface.domain.location.entity

data class Schedule(
    val open: String,
    val close: String
)

fun Schedule?.mapSchedule(weekday: String, to: String): String {
    return this?.let {
        "$weekday: $open $to $close\n"
    }.orEmpty()
}