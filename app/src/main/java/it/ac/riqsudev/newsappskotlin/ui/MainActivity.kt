package it.ac.riqsudev.newsappskotlin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import it.ac.riqsudev.newsappskotlin.R
import it.ac.riqsudev.newsappskotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        val navigationView: BottomNavigationView = binding.bottomNavBar
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        navigationView.setupWithNavController(navController)
    }
}