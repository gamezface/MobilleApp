package com.gamezface.data.remote.location.response

import com.gamezface.data.base.DataResponse
import com.gamezface.data.utils.fromJson
import com.gamezface.domain.location.entity.Location
import com.gamezface.domain.location.entity.Schedules
import com.google.gson.annotations.SerializedName

data class LocationsDataResponse(
    @SerializedName("listLocations")
    val listLocations: List<LocationDataResponse>?
) : DataResponse<List<Location>> {
    override fun toDomain(): List<Location> {
        return listLocations.orEmpty().map {
            it.toDomain()
        }
    }
}

data class LocationDataResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("review")
    val review: Float,
    @SerializedName("type")
    val type: String,
    @SerializedName("about")
    val about: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("adress")
    val address: String?,
    @SerializedName("schedule")
    val schedule: Any?
) : DataResponse<Location> {
    override fun toDomain(): Location {
        val schedules: Schedules? = schedule?.let {
            if (schedule is List<*>) {
                fromJson(schedule[0].toString())
            } else {
                fromJson(schedule.toString())
            }
        }
        return Location(id, name, review, type, about, phone, address, schedules)
    }
}