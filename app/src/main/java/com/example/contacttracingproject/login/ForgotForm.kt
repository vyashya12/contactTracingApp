package com.example.contacttracingproject.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.contacttracingproject.R

class ForgotForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_form)
    }

//    Onclick CHange Password Redirects to Login Screen
    fun BackToLogin(view: View) {
        val i = Intent(this, LoginForm::class.java)
        startActivity(i)
    }
}