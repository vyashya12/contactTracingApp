package com.example.contacttracingproject.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.contacttracingproject.base.BaseViewModel
import com.example.contacttracingproject.data.UserRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Integer.parseInt
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: UserRepositoryImpl): BaseViewModel() {
    fun getUser(ic: String) {
        repository.getUser(parseInt(ic)) { getResponse ->
            Log.i("homeViewModel", getResponse.toString())

            val user = getResponse
            fullName.value = user!!.fullName
            nric.value = user.nric
        }
    }

    // HomeViewModel factory
    class Provider(private val repository: UserRepositoryImpl): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return HomeViewModel(repository) as T
        }
    }
}

//        val user = repository.getUser()
//        viewModelScope.launch {
//            val user = repository.login(name)
//            fullName.value = user.fullName
//            nric.value = user.nric
//            login(name)
//        }