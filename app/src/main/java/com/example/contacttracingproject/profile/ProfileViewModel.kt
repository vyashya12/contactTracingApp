package com.example.contacttracingproject.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.contacttracingproject.base.BaseViewModel
import com.example.contacttracingproject.data.User
import com.example.contacttracingproject.data.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.math.BigInteger
import java.security.MessageDigest
import java.util.regex.Pattern

class ProfileViewModel(private val repository: UserRepository): BaseViewModel() {

    var editName = MutableLiveData<String>()
    var editPassword = MutableLiveData<String>()

    fun getUser(name: String) {
        viewModelScope.launch {
            val user = repository.login(name)
            fullName.value = user.fullName
            nric.value = user.nric
            login(name)
        }
    }

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

    fun updateUser(userName: String, userPassword: String) {
        if(userName.isNullOrEmpty() || userPassword.isNullOrEmpty() ||
            (!validate(userPassword))) {
            viewModelScope.launch {
                _errorToast.emit("Update Failed. Please check input fields")
            }
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                val userInfo = repository.login(fullName.value.toString())

                userInfo.fullName = userName
                userInfo.password = md5Hash(userPassword)

                update(userInfo)


            }
        }
    }

    private fun login(name: String): Job =
        viewModelScope.launch(Dispatchers.IO) {
            repository.login(name)
        }

    private fun update(user: User): Job =
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
}