package com.gamezface.mobileapp.modules.home.repository

import com.gamezface.mobileapp.core.ProjectApi
import javax.inject.Inject


class HomeRepository @Inject constructor(private val api: ProjectApi) {

    suspend fun requestLocations() = api.getLocations()
    suspend fun requestLocation(id: Long) = api.getLocation(id)
}