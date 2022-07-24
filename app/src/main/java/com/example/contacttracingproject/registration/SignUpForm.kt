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
import com.example.contacttracingproject.login.LoginForm
import com.example.contacttracingproject.databinding.ActivitySignUpFormBinding

//Sign Up form activity
class SignUpForm : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpFormBinding
    private lateinit var viewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up_form)
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)

        binding.buttonSignUp.setOnClickListener{
            if(binding.editTextPassword.text.toString() != binding.editTextPassword2.text.toString()) {
                Toast.makeText(this, "Passwords do not Match", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.fullName.value = binding.editTextPersonName.text.toString()
                viewModel.nric.value = binding.editTextIDNumber.text.toString().toInt()
                viewModel.passwd.value = binding.editTextPassword.text.toString()
                Log.i("icNo", viewModel.nric.value.toString())
                viewModel.registering()
            }
        }
    }
}