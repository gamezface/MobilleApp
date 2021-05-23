package com.gamezface.data.di

import com.gamezface.data.remote.api.ImageApi
import com.gamezface.data.remote.api.ProjectApi
import com.gamezface.data.remote.image.repository.ImageRepositoryImpl
import com.gamezface.data.remote.image.source.ImageRemoteDataSource
import com.gamezface.data.remote.location.repository.LocationRepositoryImpl
import com.gamezface.data.remote.location.source.LocationRemoteDataSource
import com.gamezface.domain.images.repository.ImageRepository
import com.gamezface.domain.location.repository.LocationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideLocationRepository(dataSource: LocationRemoteDataSource): LocationRepository {
        return LocationRepositoryImpl(dataSource)
    }

    @Singleton
    @Provides
    fun provideImageRepository(dataSource: ImageRemoteDataSource): ImageRepository {
        return ImageRepositoryImpl(dataSource)
    }

    @Singleton
    @Provides
    fun provideImageDataSource(imageApi: ImageApi): ImageRemoteDataSource {
        return ImageRemoteDataSource(imageApi)
    }

    @Singleton
    @Provides
    fun provideLocationDataSource(projectApi: ProjectApi): LocationRemoteDataSource {
        return LocationRemoteDataSource(projectApi)
    }

}