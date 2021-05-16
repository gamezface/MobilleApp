package com.gamezface.mobileapp.di

import com.gamezface.mobileapp.core.ImageApi
import com.gamezface.mobileapp.core.ProjectApi
import com.gamezface.mobileapp.modules.home.repository.HomeRepository
import com.gamezface.mobileapp.modules.home.repository.ImageRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideHomeRepository(api: ProjectApi): HomeRepository {
        return HomeRepository(api)
    }

    @Singleton
    @Provides
    fun provideImageRepository(api: ImageApi): ImageRepository {
        return ImageRepository(api)
    }

}