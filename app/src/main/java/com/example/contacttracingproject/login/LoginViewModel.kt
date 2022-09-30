package com.example.contacttracingproject.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.contacttracingproject.base.BaseViewModel
import com.example.contacttracingproject.data.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: UserRepositoryImpl): BaseViewModel() {
    // Function for validating fields before calling repository login
    fun login() {
        if(nric.value.isNullOrEmpty() ||
            passwd.value.isNullOrEmpty()) {
            viewModelScope.launch {
                _errorToast.emit("Login unsuccessful. Please retry.")
                _finish.value = false
            }
        } else {
            repository.login(LoginModel(nric.value.toString(), passwd.value.toString())) { loginResponse ->
                if (loginResponse!!.nric != 0) { // valid user with correct password
                    passwd.value = null
                    _finish.value = true
                    Log.i("loginViewModel", loginResponse.toString())
                    Log.i("loginViewModel", loginResponse.nric.toString())
                } else {   // invalid password
                    viewModelScope.launch {
                        _errorToast.emit("Invalid Password")
                    }
                }
            }
        }
    }

    // LoginFormViewModel factory
    class Provider(private val repository: UserRepositoryImpl): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return LoginViewModel(repository) as T
        }
    }
}

