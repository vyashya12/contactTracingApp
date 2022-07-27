package com.example.contacttracingproject.registration

import android.app.Application
import android.content.Intent
import android.util.Log
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import com.example.contacttracingproject.base.BaseViewModel
import com.example.contacttracingproject.data.User
import com.example.contacttracingproject.data.UserRepository
import java.math.BigInteger
import java.security.MessageDigest
import java.util.regex.Pattern

class SignUpViewModel(private val repository: UserRepository): BaseViewModel(){

    fun validate(input: String): Boolean {
        val PATTERN: Pattern =
            Pattern.compile("^" +
                    "(?=.*[@#$%^&+=])" +
                    "(?=\\S+$)" +
                    ".{6,}" +
                    "$")

        return PATTERN.matcher(input).matches()
    }

    fun md5Hash(input: String): String {
        val passwordToHash: String = input
        var hashedPassword: String? = null

        val md = MessageDigest.getInstance("MD5")
        hashedPassword = BigInteger(1, md.digest(passwordToHash.toByteArray())).toString(16).padStart(32, '0')

        return hashedPassword
    }
    fun registering() {  // Empty field and validate username and password
        Log.i("icNo", fullName.value.toString())
        Log.i("icNo1", nric.value.toString())
        Log.i("icNo2", passwd.value.toString())
        if((fullName.value.isNullOrEmpty()) ||
                (nric.value.isNullOrEmpty()) ||
                (passwd.value.isNullOrEmpty()) ||
            (!validate(passwd.value.toString()))) {
            _errorToast.value = true
            Log.i("icNo6", "Spmething is still trruue")
        } else {  // register
            viewModelScope.launch {
                val userInfo = repository.login(fullName.value.toString())
                if(userInfo == null) {  // No existing record with the same username
                    val userName: String = fullName.value!!
                    val icNo: String = nric.value!!
                    val password: String = md5Hash(passwd.value!!)
                    val user = User(0,userName,icNo,password)
                    register(user)
                    fullName.value = null
                    nric.value = null
                    passwd.value = null
                } else {
                    _errorToastUserName.value = true
                }
            }
        }
    }

    private fun register(user: User): Job =
        viewModelScope.launch(Dispatchers.IO) {
            repository.register(user)
        }
}