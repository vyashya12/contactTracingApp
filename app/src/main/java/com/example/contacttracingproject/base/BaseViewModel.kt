package com.example.contacttracingproject.base

import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.contacttracingproject.data.User

abstract class BaseViewModel: ViewModel() {
    val fullName = MutableLiveData<String>()

    val nric = MutableLiveData<Int>()

    val passwd = MutableLiveData<String>()

    val _errorToast = MutableLiveData<Boolean>(false)

    val _errorToastUserName = MutableLiveData<Boolean>(false)
}