package com.gamezface.data.remote.location.repository

import com.gamezface.data.remote.location.source.LocationRemoteDataSource
import com.gamezface.domain.location.entity.Location
import com.gamezface.domain.location.repository.LocationRepository
import javax.inject.Inject


class LocationRepositoryImpl @Inject constructor(
    private val remoteDataSource: LocationRemoteDataSource
) : LocationRepository {

    override suspend fun retrieveLocations(): List<Location> {
        try {
            val locations = remoteDataSource.getLocations()
            return locations.toDomain()
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun retrieveLocation(id: Long): Location {
        try {
            val locations = remoteDataSource.getLocation(id)
            return locations.toDomain()
        } catch (e: Exception) {
            throw e
        }
    }
}