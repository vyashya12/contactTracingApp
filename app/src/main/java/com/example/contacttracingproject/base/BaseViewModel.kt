package com.example.contacttracingproject.base

import android.app.Application
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

abstract class BaseViewModel(): ViewModel(),
    Observable {

    protected val viewModelJob = Job()
    protected val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    @Bindable
    val fullName = MutableLiveData<String>()

    @Bindable
    val nric = MutableLiveData<String>()

    @Bindable
    val passwd = MutableLiveData<String>()

    @Bindable
    val passwd2 = MutableLiveData<String>()

    val _errorToast = MutableLiveData<Boolean>(false)
    val errorToast: LiveData<Boolean>
        get() = _errorToast

    val _errorToastUserName = MutableLiveData<Boolean>(false)
    val errorToastUserName: LiveData<Boolean>
        get() = _errorToastUserName

    val _errorToastPassword = MutableLiveData<Boolean>(false)
    val errorToastPassword: LiveData<Boolean>
        get() = _errorToastPassword

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}