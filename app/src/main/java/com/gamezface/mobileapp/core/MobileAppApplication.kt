package com.gamezface.mobileapp.core

import android.app.Application
import com.gamezface.mobileapp.di.ApplicationComponent
import com.gamezface.mobileapp.di.DaggerApplicationComponent

class MobileAppApplication: Application() {
    val appComponent: ApplicationComponent = DaggerApplicationComponent.create()
}