package com.gamezface.domain.images.repository

import com.gamezface.domain.images.entity.Image

interface ImageRepository {
    suspend fun retrieveImages(limit: Int): List<Image>
}