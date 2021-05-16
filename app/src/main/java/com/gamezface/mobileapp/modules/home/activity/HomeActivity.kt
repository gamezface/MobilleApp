package com.gamezface.mobileapp.modules.home.activity

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.gamezface.mobileapp.R
import com.gamezface.mobileapp.core.MobileAppApplication
import com.gamezface.mobileapp.core.utils.hide
import com.gamezface.mobileapp.core.utils.show
import com.gamezface.mobileapp.databinding.ActivityHomeBinding
import com.gamezface.mobileapp.modules.home.di.HomeComponent
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    lateinit var homeComponent: HomeComponent
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottomNavigation()

        homeComponent = (applicationContext as MobileAppApplication)
            .appComponent.homeComponent().create()

        homeComponent.inject(this)
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigationView.itemIconTintList = null
        findNavController(R.id.fragmentContainerView).addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id) {
                R.id.homeFragment -> binding.bottomNavigationView.show()
                else -> binding.bottomNavigationView.hide()
            }
        }
    }
}