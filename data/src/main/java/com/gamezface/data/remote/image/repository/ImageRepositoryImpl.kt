package com.gamezface.data.remote.image.repository

import com.gamezface.data.remote.image.source.ImageRemoteDataSource
import com.gamezface.domain.images.entity.Image
import com.gamezface.domain.images.repository.ImageRepository
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val remoteDataSource: ImageRemoteDataSource
) : ImageRepository {
    override suspend fun retrieveImages(limit: Int): List<Image> {
        try {
            val images = remoteDataSource.getImages(limit)
            return images.map { it.toDomain() }
        } catch (e: Exception) {
            throw e
        }
    }
}