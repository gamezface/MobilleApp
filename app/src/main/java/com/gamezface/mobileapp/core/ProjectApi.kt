package com.gamezface.mobileapp.core
import com.gamezface.mobileapp.modules.home.models.Locations
import retrofit2.http.*

interface ProjectApi {
    @GET("locations")
    suspend fun getLocations(): Locations
}