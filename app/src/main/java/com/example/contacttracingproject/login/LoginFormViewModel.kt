package com.example.contacttracingproject.login

import androidx.lifecycle.viewModelScope
import com.example.contacttracingproject.base.BaseViewModel
import com.example.contacttracingproject.data.User
import com.example.contacttracingproject.data.UserRepository
import kotlinx.coroutines.launch
import java.math.BigInteger
import java.security.MessageDigest

class LoginFormViewModel(private val repository: UserRepository): BaseViewModel() {

    fun md5Hash(input: String): String {
        val passwordToHash: String = input
        var hashedPassword: String? = null

        val md = MessageDigest.getInstance("MD5")
        hashedPassword = BigInteger(1, md.digest(passwordToHash.toByteArray())).toString(16).padStart(32, '0')

        return hashedPassword
    }

    fun login() {
        if(fullName.value.isNullOrEmpty() ||
            passwd.value.isNullOrEmpty()) {
            _errorToast.value = true
        } else {
            viewModelScope.launch {
                val user: User = repository.login(fullName.value.toString())
                if(user != null) {
                    if(user.password == md5Hash(passwd.value.toString())) {  // valid user with correct password
                        fullName.value = null
                        passwd.value = null
                    } else {   // invalid password
                        _errorToastPassword.value = true
                    }
                } else {  // username does not exist (invalid username)
                    _errorToastUserName.value = true
                }
            }
        }
    }
}