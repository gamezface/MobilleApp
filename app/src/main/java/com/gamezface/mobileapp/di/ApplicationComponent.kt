package com.gamezface.mobileapp.di

import com.gamezface.mobileapp.core.MobileAppApplication
import com.gamezface.mobileapp.modules.home.di.HomeComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, RepositoryModule::class])
interface ApplicationComponent {
    fun inject(app: MobileAppApplication)
    fun homeComponent(): HomeComponent.Factory
}