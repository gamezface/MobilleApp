package com.gamezface.data.base


interface DataResponse<D> {
    fun toDomain(): D
}