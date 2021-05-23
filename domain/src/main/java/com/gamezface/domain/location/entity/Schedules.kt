package com.gamezface.domain.location.entity

data class Schedules(
    val monday: Schedule?,
    val tuesday: Schedule?,
    val wednesday: Schedule?,
    val thursday: Schedule?,
    val friday: Schedule?,
    val saturday: Schedule?,
    val sunday: Schedule?
)

fun Schedules.mapSchedules(weekdays: Array<String>, to: String): String {
    val sunday = this.sunday.mapSchedule(weekdays[0], to)
    val monday = this.monday.mapSchedule(weekdays[1], to)
    val tuesday = this.monday.mapSchedule(weekdays[2], to)
    val wednesday = this.monday.mapSchedule(weekdays[3], to)
    val thursday = this.monday.mapSchedule(weekdays[4], to)
    val friday = this.monday.mapSchedule(weekdays[5], to)
    val saturday = this.monday.mapSchedule(weekdays[6], to)
    return "$sunday$monday$tuesday$wednesday$thursday$friday$saturday"
}