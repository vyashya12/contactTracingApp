package com.example.contacttracingproject.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.contacttracingproject.base.BaseViewModel
import com.example.contacttracingproject.data.User
import com.example.contacttracingproject.data.UserRepository

class HomeViewModel(private val repository: UserRepository): BaseViewModel() {

    suspend fun getUser() {
        val user: User = repository.login(fullName.value.toString())
        fullName.value = user.fullName
        nric.value = user.nric
    }
}