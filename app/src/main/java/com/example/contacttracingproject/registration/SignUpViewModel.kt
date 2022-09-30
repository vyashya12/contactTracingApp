package com.example.contacttracingproject.registration

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.*
import com.example.contacttracingproject.base.BaseViewModel
import com.example.contacttracingproject.data.*
import dagger.hilt.android.lifecycle.HiltViewModel
import java.math.BigInteger
import java.security.MessageDigest
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val repository: UserRepositoryImpl): BaseViewModel() {
    // Function for checking if password follows required format
    fun validate(input: String): Boolean {
        val PATTERN: Pattern =
            Pattern.compile(
                "^" +
                "(?=.*[@#$%^&+=])" +
                "(?=\\S+$)" +
                ".{6,}" +
                "$"
            )

        return PATTERN.matcher(input).matches()
    }

    // Function for validating fields before calling repository register
    fun registering() {  // Empty field and validate username and password
        if ((fullName.value.isNullOrEmpty()) || (nric.value.isNullOrEmpty()) ||
            (passwd.value.isNullOrEmpty()) || (passwd.value != passwd2.value) ||
            (!validate(passwd.value.toString()))
        ) {
            viewModelScope.launch {
                _errorToast.emit("Registration Failed. Please check input fields")
                _finish.value = false
            }
        } else {  // Register
            repository.login(LoginModel(nric.value.toString(), passwd.value.toString())) { response ->
                Log.i("signUpViewModel", response.toString())
                if (response!!.nric == 0) { // User does not exist, LoginResponse nric property is always initialised as 0 because it is of type Int
                    Log.i("signUpViewModel", "No pre-existing account")
                    repository.register(SignUpModel(fullName.value.toString(), nric.value.toString(), passwd.value.toString())) { signUpResponse ->
                        if (signUpResponse != null) { // Successful registration
                            _finish.value = true
                            Log.i("signUpViewModel", "Sign up successful")
                            Log.i("signUpViewModel", signUpResponse.message)
                            Log.i("signUpViewModel", signUpResponse.user.toString())
                        } else {
                            Log.i("signUpViewModel", "Sign up failed")
                        }
                    }
                } else { // User already exists
                    viewModelScope.launch {
                        _errorToast.emit("User exist. Please proceed to Log In.")
                    }
                }
            }
        }
    }

    // SignUpViewModel factory
    class Provider(private val repository: UserRepositoryImpl) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return SignUpViewModel(repository) as T
        }
    }
}