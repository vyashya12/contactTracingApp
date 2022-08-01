package com.example.contacttracingproject.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.contacttracingproject.*
import com.example.contacttracingproject.history.*
import com.example.contacttracingproject.profile.Profile
import com.example.contacttracingproject.stats.Statistics
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

//HomeActivity contains the BottomNavigation
class HomeActivity : AppCompatActivity() {
    private lateinit var historyViewModel: HistoryViewModel

    val zXingQRCodeScanLauncher = registerForActivityResult(ScanContract()) {
            result ->
        // Get callback results
        if (result.contents == null) {
            Toast.makeText(
                applicationContext,
                " Cancel ",
                Toast.LENGTH_LONG
            ).show()

            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_activity_home, History.newInstance())
                .commit()

        } else {
            val intent = intent
            val fullname = intent.getStringExtra("fullname").toString()

            // Get location information
            val address_array = result.contents.toString().split(",").toMutableList()

            val name = address_array[0]
            address_array.removeAt(0)
            val address = address_array.joinToString(",")

            val current_date = LocalDate.now().toString()
            val current_time = LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm")).toString()
            val history = HistorySchema(null, fullname, name, address, current_date, current_time)

            val dao = HistoryDatabase.getDatabase(this).historyDao()

            val repository = HistoryRepository(dao)

            val factory = HistoryViewModelFactory(repository)

            historyViewModel = ViewModelProvider(this, factory).get(HistoryViewModel::class.java)

            historyViewModel.insert(history)
        }
    }

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
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_home, Home.newInstance())
                        .commit()
                    true
                }
                R.id.nav_history -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_home, History.newInstance())
                        .commit()
                    true
                }
                R.id.nav_scan -> {
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.nav_host_fragment_activity_home, Scanner.newInstance())
//                        .commit()

                    // ZXing launch
                    val options = ScanOptions()
                    options.apply {
                        setOrientationLocked(false)
                        setDesiredBarcodeFormats(ScanOptions.QR_CODE)
                        setPrompt("Scan")
                        setCameraId(0)
                        setBeepEnabled(true)
                        setBarcodeImageEnabled(true)
                        setOrientationLocked(true)
                    }

                    zXingQRCodeScanLauncher.launch(options)

                    true
                }
                R.id.nav_statistics -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_home, Statistics.newInstance())
                        .commit()
                    true
                }
                R.id.nav_profile -> {
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