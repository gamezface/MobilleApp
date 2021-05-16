package com.gamezface.mobileapp.core

import com.gamezface.mobileapp.modules.home.models.Image
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageApi {
    @GET("list")
    suspend fun getImages(@Query("limit") limit: Int): List<Image>
}