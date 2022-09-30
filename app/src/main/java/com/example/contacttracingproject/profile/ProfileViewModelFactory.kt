package com.example.contacttracingproject.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.contacttracingproject.data.UserRepository
import com.example.contacttracingproject.data.UserRepositoryImpl
import com.example.contacttracingproject.profile.ProfileViewModel

class ProfileViewModelFactory(private  val repository: UserRepositoryImpl): ViewModelProvider.Factory{
    @Suppress("Unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}