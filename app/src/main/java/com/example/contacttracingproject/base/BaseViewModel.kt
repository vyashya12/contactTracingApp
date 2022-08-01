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

    protected var viewModelJob = Job()
    protected var uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    // User
    @Bindable
    var fullName = MutableLiveData<String>()

    @Bindable
    var nric = MutableLiveData<String>()

    @Bindable
    var passwd = MutableLiveData<String>()

    @Bindable
    var passwd2 = MutableLiveData<String>()

    var _errorToast: MutableSharedFlow<String> = MutableSharedFlow()
    var errorToast: SharedFlow<String> = _errorToast

    var _finish = MutableLiveData<Boolean>()
    var finish: LiveData<Boolean> = _finish

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}