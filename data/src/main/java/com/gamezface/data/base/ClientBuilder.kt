package com.gamezface.data.base

import com.gamezface.data.BuildConfig
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ClientBuilder {
    companion object {
        fun <S> createService(
            serviceClass: Class<S>,
            baseUrl: String,
            gson: Gson
        ): S = createRetrofitClient(gson, baseUrl).create(serviceClass)

        private fun createInterceptors(): ArrayList<Interceptor> {
            val interceptors = arrayListOf<Interceptor>()
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            }
            interceptors.add(loggingInterceptor)
            return interceptors
        }

        private fun createOkHttpClient(): OkHttpClient {
            val clientBuilder = OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .followRedirects(false)
            val interceptors = createInterceptors()
            interceptors.forEach {
                clientBuilder.addInterceptor(it)
            }
            return clientBuilder.build()
        }

        private fun createRetrofitClient(gson: Gson, baseUrl: String): Retrofit {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseUrl)
                .client(createOkHttpClient())
                .build()
        }
    }
}