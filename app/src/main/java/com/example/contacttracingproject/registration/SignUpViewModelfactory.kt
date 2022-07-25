package com.example.contacttracingproject.registration

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.contacttracingproject.data.UserRepository

class SignUpViewModelfactory(
        private  val repository: UserRepository): ViewModelProvider.Factory{
        @Suppress("Unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
                return SignUpViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown View Model Class")
        }
}