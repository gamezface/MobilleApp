package com.gamezface.mobileapp.modules.home.models

import com.google.gson.annotations.SerializedName

data class Image(
    val id: String?,
    val author: String?,
    val width: String?,
    val height: String?,
    val url: String?,
    @SerializedName("download_url") val downloadUrl: String?
)
