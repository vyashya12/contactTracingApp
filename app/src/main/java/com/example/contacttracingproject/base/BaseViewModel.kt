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
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

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

    val _errorToast: MutableSharedFlow<String> = MutableSharedFlow()
    val errorToast: SharedFlow<String> = _errorToast

    val _finish = MutableLiveData<Boolean>()
    val finish: LiveData<Boolean> = _finish

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}