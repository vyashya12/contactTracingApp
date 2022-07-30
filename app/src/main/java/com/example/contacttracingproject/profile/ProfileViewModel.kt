package com.example.contacttracingproject.profile

import android.util.Log
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

class ProfileViewModel(private val repository: UserRepository): BaseViewModel() {

    fun getUser(name: String) {
        viewModelScope.launch {
            val user = repository.login(name)
            fullName.value = user.fullName
            nric.value = user.nric
            login(name)
        }
    }

    fun md5Hash(input: String): String {
        val passwordToHash: String = input
        var hashedPassword: String? = null

        val md = MessageDigest.getInstance("MD5")
        hashedPassword = BigInteger(1, md.digest(passwordToHash.toByteArray())).toString(16).padStart(32, '0')

        return hashedPassword
    }

    fun updateUser() {
        viewModelScope.launch {
//            val userInfo = repository.login(fullName.value.toString())
//            val icNo: String = nric.value!!
//            val password: String = md5Hash(passwd.value!!)
//            val user = User(0,userName,icNo,password)
//            update(userInfo)
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