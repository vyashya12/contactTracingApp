package com.example.contacttracingproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.contacttracingproject.registration.SignUpForm

//Main welcome screen activity
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

//    Onclick opens the sign up screen
    fun SignUp(view: View) {
        val i = Intent(this, SignUpForm::class.java)
        startActivity(i)
    }

//    Onclick opens the LoginForm
    fun LoginForm(view: View) {
        val i = Intent(this, com.example.contacttracingproject.login.LoginForm::class.java)
        startActivity(i)
    }
}