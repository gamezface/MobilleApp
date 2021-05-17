package com.gamezface.mobileapp.modules.home.models

import java.io.Serializable

data class Schedules(val monday: Schedule, val tuesday: Schedule, val wednesday: Schedule, val thursday: Schedule, val friday: Schedule, val saturday: Schedule, val sunday: Schedule) : Serializable

