package com.gamezface.data.remote.image.source

import com.gamezface.data.base.BaseRemoteDataSource
import com.gamezface.data.remote.api.ImageApi
import javax.inject.Inject

class ImageRemoteDataSource @Inject constructor(
    private val imageApi: ImageApi
) : BaseRemoteDataSource() {
    suspend fun getImages(limit: Int) = getResult { imageApi.getImages(limit) }
}