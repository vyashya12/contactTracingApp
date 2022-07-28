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
            viewModelScope.launch {
                _errorToast.emit("Login unsuccessful. Please retry.")
                _finish.value = false
            }
        } else {
            viewModelScope.launch {
                val user: User = repository.login(fullName.value.toString())
                if(user != null) {
                    if(user.password == md5Hash(passwd.value.toString())) {  // valid user with correct password
                        passwd.value = null
                        _finish.value = true
                    } else {   // invalid password
                        viewModelScope.launch {
                            _errorToast.emit("Invalid Password")
                        }
                    }
                } else {  // username does not exist (invalid username)
                    _errorToast.emit("Username does not exist.")
                }
            }
        }
    }
}