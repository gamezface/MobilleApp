package com.gamezface.mobileapp.features

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.gamezface.mobileapp.R
import com.gamezface.mobileapp.databinding.ActivityHomeBinding
import com.gamezface.mobileapp.extensions.hide
import com.gamezface.mobileapp.extensions.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigationView.itemIconTintList = null
        val navHost =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navHost.navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> binding.bottomNavigationView.show()
                else -> binding.bottomNavigationView.hide()
            }
        }
    }
}