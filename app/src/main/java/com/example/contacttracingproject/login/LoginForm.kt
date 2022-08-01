package com.example.contacttracingproject.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.contacttracingproject.home.HomeActivity
import com.example.contacttracingproject.R
import com.example.contacttracingproject.data.UserDatabase
import com.example.contacttracingproject.data.UserRepository
import com.example.contacttracingproject.databinding.ActivityLoginFormBinding
import com.example.contacttracingproject.registration.SignUpViewModel
import com.example.contacttracingproject.registration.SignUpViewModelfactory

//Login Form Activity
class LoginForm : AppCompatActivity() {
    private lateinit var binding: ActivityLoginFormBinding
    private lateinit var loginFormViewModel: LoginFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_form)

        val dao = UserDatabase.getInstance(this).userDAO()

        val repository = UserRepository(dao)

        val factory = LoginFormViewModelFactory(repository)

        loginFormViewModel = ViewModelProvider(this, factory).get(LoginFormViewModel::class.java)

        binding.viewModel = loginFormViewModel

        binding.lifecycleOwner = this

        loginFormViewModel.errorToast.asLiveData().observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            loginFormViewModel._finish.value = false
        }

        loginFormViewModel.finish.observe(this, Observer { isFinished ->
            if (isFinished == true) {
                SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Login Successful").setConfirmClickListener {
                        val intent = Intent(this, HomeActivity::class.java)
                            .putExtra("fullname", loginFormViewModel.fullName.value.toString())
//                        Log.i("fullname", loginFormViewModel.fullName.value.toString())
                        startActivity(intent)
                    }
                    .show()
            }
        })



        binding.button.setOnClickListener {
            loginFormViewModel.login()
        }
    }

    //    Onclick opens the Password reset screen
    fun ResetPassword(view: View) {
        val i = Intent(this, ForgotForm::class.java)
        startActivity(i)
    }
}