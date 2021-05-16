package com.gamezface.mobileapp.modules.home.repository

import com.gamezface.mobileapp.core.ImageApi
import javax.inject.Inject

class ImageRepository @Inject constructor(private val imageApi: ImageApi) {
    suspend fun requestImages(limit: Int) = imageApi.getImages(limit)
}