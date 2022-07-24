package com.example.contacttracingproject.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.contacttracingproject.home.HomeActivity
import com.example.contacttracingproject.R

//Login Form Activity
class LoginForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_form)
    }

//    Onclick opens the Password reset screen
    fun ResetPassword(view: View) {
        val i = Intent(this, ForgotForm::class.java)
        startActivity(i)
    }

//    Onclick Login Goes to the Home Screen
    fun HomeIntent(view: View) {
        val i = Intent(this, HomeActivity::class.java)
        startActivity(i)
    }
}