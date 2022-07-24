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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up_form)
        val viewModel: SignUpViewModel by lazy {
            ViewModelProvider(this).get(SignUpViewModel::class.java)
        }

        binding.buttonSignUp.setOnClickListener{
            Log.i("icNo", "Hello")
            if(binding.editTextPassword.text.toString() != binding.editTextPassword2.text.toString()) {
                Toast.makeText(this, "Passwords do not Match", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.fullName.value = binding.editTextPersonName.text.toString()
                viewModel.nric.value = binding.editTextIDNumber.text.toString().toInt()
                viewModel.passwd.value = binding.editTextPassword.text.toString()

                Log.i("icNo", viewModel.fullName.value.toString())
                viewModel.registering()
                                Log.i("icNo", "It hits")
                val intent = Intent(this, LoginForm::class.java)
                startActivity(intent)
            }
        }
    }
}