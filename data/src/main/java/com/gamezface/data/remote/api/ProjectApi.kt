package com.gamezface.data.remote.api

import com.gamezface.data.remote.location.response.LocationDataResponse
import com.gamezface.data.remote.location.response.LocationsDataResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ProjectApi {
    @GET("locations")
    suspend fun getLocations(): LocationsDataResponse

    @GET("locations/{id}")
    suspend fun getLocation(@Path("id") id: Long): LocationDataResponse
}