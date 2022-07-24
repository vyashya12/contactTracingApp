package com.example.contacttracingproject

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity

//Splash screen, shown for 3 seconds upon app launch
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
//        val corolo_splash = findViewById<View>(R.id.corolo_splash)
//        val animation = AnimationUtils.loadAnimation(
//            this@SplashActivity,
//            R.anim.blink
//        )
//        corolo_splash.startAnimation(animation)

        // Splash screen delay
        Handler().postDelayed({
            val i = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(i)
            finish()
        }, 3000)
    }
}