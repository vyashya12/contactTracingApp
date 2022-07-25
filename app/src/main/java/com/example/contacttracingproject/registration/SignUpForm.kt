package com.example.contacttracingproject.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.contacttracingproject.R
import com.example.contacttracingproject.data.UserDatabase
import com.example.contacttracingproject.data.UserRepository
import com.example.contacttracingproject.login.LoginForm
import com.example.contacttracingproject.databinding.ActivitySignUpFormBinding

//Sign Up form activity
class SignUpForm : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpFormBinding
    private lateinit var signUpViewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up_form)

        signUpViewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)

        binding.viewModel = signUpViewModel

        binding.lifecycleOwner = this
    }
}