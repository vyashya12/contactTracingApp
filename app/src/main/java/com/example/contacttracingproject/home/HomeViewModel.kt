package com.example.contacttracingproject.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.contacttracingproject.base.BaseViewModel
import com.example.contacttracingproject.data.User
import com.example.contacttracingproject.data.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: UserRepository): BaseViewModel() {

    fun getUser(name: String) {
        viewModelScope.launch {
            val user = repository.login(name)
            fullName.value = user.fullName
            nric.value = user.nric
            login(name)
        }
    }


    private fun login(name: String): Job =
        viewModelScope.launch(Dispatchers.IO) {
            repository.login(name)
        }
}