package com.example.contacttracingproject.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.contacttracingproject.R
import com.example.contacttracingproject.data.UserDatabase
import com.example.contacttracingproject.data.UserRepository
import com.example.contacttracingproject.databinding.ActivitySignUpFormBinding
import com.example.contacttracingproject.login.LoginForm


//Sign Up form activity
class SignUpForm : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpFormBinding
    private lateinit var signUpViewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up_form)

        val dao = UserDatabase.getInstance(this).userDAO()

        val repository = UserRepository(dao)

        val factory = SignUpViewModelfactory(repository)

        signUpViewModel = ViewModelProvider(this, factory).get(SignUpViewModel::class.java)

        binding.viewModel = signUpViewModel

        binding.lifecycleOwner = this

        signUpViewModel.errorToast.observe(this, Observer { hasError ->
            if (hasError == true) {
                Toast.makeText(this, "Invalid Input fields", Toast.LENGTH_SHORT).show()
            }
        })

        signUpViewModel.errorToastUserName.observe(this, Observer { userNameExists ->
            if (userNameExists == true) {
                Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT).show()
            }
        })

        signUpViewModel.errorToastPassword.observe(this, Observer { passwordMatches ->
            if (passwordMatches == true) {
                Toast.makeText(this, "Passwords do not Match", Toast.LENGTH_SHORT).show()
            }
        })

        binding.buttonSignUp.setOnClickListener {
            signUpViewModel.registering()
            if (signUpViewModel.errorToast.value == false &&
                signUpViewModel.errorToastUserName.value == false &&
                signUpViewModel.errorToastPassword.value == false
            ) {
                SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Register Successful").setConfirmClickListener {
                        val intent = Intent(this, LoginForm::class.java)
                        startActivity(intent)
                    }
                    .show()
            }
        }
    }
}