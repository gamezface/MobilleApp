package com.gamezface.data.remote.image.response

import com.gamezface.data.base.DataResponse
import com.gamezface.domain.images.entity.Image
import com.google.gson.annotations.SerializedName

data class ImageDataResponse(
    val id: String?,
    val author: String?,
    val width: String?,
    val height: String?,
    val url: String?,
    @SerializedName("download_url")
    val downloadUrl: String?
) : DataResponse<Image> {
    override fun toDomain(): Image {
        return Image(id, author, width, height, url, downloadUrl)
    }

}