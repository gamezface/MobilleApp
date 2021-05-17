package com.gamezface.mobileapp.core.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GsonUtil {
    inline fun <reified T> fromJson(json: String): T? {
        return Gson().fromJson(json, object : TypeToken<T>() {}.type)
    }

    fun <T> mapToObject(map: Map<String, Any?>?, type: Class<T>): T? {
        if (map == null) return null

        val json = Gson().toJson(map)
        return Gson().fromJson(json, type)
    }
}