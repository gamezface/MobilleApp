package com.gamezface.data.di

import com.gamezface.data.BuildConfig.BASE_URL
import com.gamezface.data.BuildConfig.IMAGE_API_URL
import com.gamezface.data.base.ClientBuilder
import com.gamezface.data.remote.api.ImageApi
import com.gamezface.data.remote.api.ProjectApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideApi(gson: Gson): ProjectApi {
        return ClientBuilder.createService(ProjectApi::class.java, BASE_URL, gson)
    }

    @Singleton
    @Provides
    fun provideImageApi(gson: Gson): ImageApi {
        return ClientBuilder.createService(ImageApi::class.java, IMAGE_API_URL, gson)
    }
}