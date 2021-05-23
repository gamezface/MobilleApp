package com.gamezface.data.remote.location.source

import com.gamezface.data.base.BaseRemoteDataSource
import com.gamezface.data.remote.api.ProjectApi
import javax.inject.Inject

class LocationRemoteDataSource @Inject constructor(
    private val projectApi: ProjectApi
) : BaseRemoteDataSource() {
    suspend fun getLocations() = getResult { projectApi.getLocations() }

    suspend fun getLocation(id: Long) = getResult { projectApi.getLocation(id) }
}