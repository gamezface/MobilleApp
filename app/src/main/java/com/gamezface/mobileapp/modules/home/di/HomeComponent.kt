package com.gamezface.mobileapp.modules.home.di

import com.gamezface.mobileapp.modules.home.activity.HomeActivity
import com.gamezface.mobileapp.modules.home.fragments.LocationFragment
import com.gamezface.mobileapp.modules.home.fragments.HomeFragment
import dagger.Subcomponent


@Subcomponent
interface HomeComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): HomeComponent
    }

    fun inject(activity: HomeActivity)
    fun inject(homeFragment: HomeFragment)
    fun inject(locationFragment: LocationFragment)
}
