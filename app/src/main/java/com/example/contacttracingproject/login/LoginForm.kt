package com.example.contacttracingproject.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.contacttracingproject.home.HomeActivity
import com.example.contacttracingproject.R
import com.example.contacttracingproject.databinding.ActivityLoginFormBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

//Login Form Activity
@AndroidEntryPoint
class LoginForm : AppCompatActivity() {
    private lateinit var binding: ActivityLoginFormBinding
    val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_form)

        binding.viewModel = loginViewModel

        binding.lifecycleOwner = this

        loginViewModel.errorToast.asLiveData().observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            loginViewModel._finish.value = false
        }

        loginViewModel.finish.observe(this, Observer { isFinished ->
            if (isFinished == true) {
                SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Login Successful").setConfirmClickListener {
                        val intent = Intent(this, HomeActivity::class.java)
                            .putExtra("nric", loginViewModel.nric.value.toString())
                        startActivity(intent)
                    }
                    .show()
            }
        })

        binding.button.setOnClickListener {
            lifecycleScope.launch {
                loginViewModel.login()
            }
        }
    }

    //    Onclick opens the Password reset screen
    fun ResetPassword(view: View) {
        val i = Intent(this, ForgotForm::class.java)
        startActivity(i)
    }
}