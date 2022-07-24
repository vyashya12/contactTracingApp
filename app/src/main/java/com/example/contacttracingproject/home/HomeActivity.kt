package com.example.contacttracingproject.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.contacttracingproject.*
import com.example.contacttracingproject.history.History
import com.example.contacttracingproject.history.Scanner
import com.example.contacttracingproject.profile.Profile
import com.example.contacttracingproject.stats.Statistics
import com.google.android.material.bottomnavigation.BottomNavigationView

//HomeActivity contains the BottomNavigation
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Change activity_home to activity_main to show the login page on app startup
        setContentView(R.layout.activity_home)

        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_activity_home, Home.newInstance())
            .commit()

        val bottom_nav_view = findViewById<BottomNavigationView>(R.id.bottom_nav_view)

        bottom_nav_view.setOnItemSelectedListener {
                item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    //navController.navigate(R.id.homeFragment)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_home, Home.newInstance())
                        .commit()
                    true
                }
                R.id.nav_history -> {
                    //navController.navigate(R.id.historyFragment)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_home, History.newInstance())
                        .commit()
                    true
                }
                R.id.nav_scan -> {
                    //navController.navigate(R.id.scanFragment)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_home, Scanner.newInstance())
                        .commit()
                    true
                }
                R.id.nav_statistics -> {
                    //navController.navigate(R.id.statisticsFragment)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_home, Statistics.newInstance())
                        .commit()
                    true
                }
                R.id.nav_profile -> {
                    //navController.navigate(R.id.profileFragment)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_home, Profile.newInstance())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }

//    OnClick Update Health Status opens the HealthForm screen
    fun HealthForm(view: View) {
        val i = Intent(this, HealthForm::class.java)
        startActivity(i)
    }
}