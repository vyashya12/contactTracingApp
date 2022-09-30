package com.example.contacttracingproject.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.contacttracingproject.base.BaseViewModel
import com.example.contacttracingproject.data.UserRepository
import kotlinx.coroutines.launch
import java.math.BigInteger
import java.security.MessageDigest
import java.util.regex.Pattern
import android.util.Log
import com.example.contacttracingproject.data.LoginModel
import com.example.contacttracingproject.data.User
import com.example.contacttracingproject.data.UserRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repository: UserRepositoryImpl): BaseViewModel() {
    var editName = MutableLiveData<String>()
    var editPassword = MutableLiveData<String>()

    fun getUser(ic: String) {
        repository.getUser(Integer.parseInt(ic)) { getResponse ->
//            Log.i("profileViewModel", getResponse.toString())

            val user = getResponse
            fullName.value = user!!.fullName
            nric.value = user.nric
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

    fun updateUser(userName: String, userNRIC: String, userPassword: String) {
        Log.i("profileViewModel", "${userName}, ${userNRIC}, ${userPassword}")
        if(userName.isNullOrEmpty() || userPassword.isNullOrEmpty() ||
            (!validate(userPassword))) {
            Log.i("profileViewModel", "profileViewModel failed")
            viewModelScope.launch {
                _errorToast.emit("Please check input fields")
            }
        } else {
            Log.i("profileViewModel", "profileViewModel success")
            repository.editUser(User(fullName = userName, nric = userNRIC, password = userPassword)) { editResponse ->
                Log.i("profileViewModel", "${userName}, ${userNRIC}, ${userPassword}")
                val user = editResponse
                fullName.value = user!!.fullName
                passwd.value = user.password

                repository.getUser(Integer.parseInt(userNRIC)) { getResponse ->
                    Log.i("profileViewModel", getResponse.toString())

                    fullName.value = getResponse!!.fullName
                    nric.value = getResponse.nric
                }
            }
        }
    }
}