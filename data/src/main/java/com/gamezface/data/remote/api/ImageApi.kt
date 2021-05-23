package com.gamezface.data.remote.api

import com.gamezface.data.remote.image.response.ImageDataResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageApi {
    @GET("list")
    suspend fun getImages(@Query("limit") limit: Int): List<ImageDataResponse>
}