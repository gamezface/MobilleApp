package com.gamezface.domain.location.repository

import com.gamezface.domain.location.entity.Location

interface LocationRepository {
    suspend fun retrieveLocations(): List<Location>
    suspend fun retrieveLocation(id: Long): Location
}