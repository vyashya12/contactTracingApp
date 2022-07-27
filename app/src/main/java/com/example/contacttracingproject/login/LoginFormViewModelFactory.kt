package com.example.contacttracingproject.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.contacttracingproject.data.UserRepository

class LoginFormViewModelFactory(private  val repository: UserRepository): ViewModelProvider.Factory{
    @Suppress("Unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LoginFormViewModel::class.java)) {
            return LoginFormViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}