package com.example.contacttracingproject.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.Flow

class HistoryViewModel(private val repository: HistoryRepository) : ViewModel() {
    val allHistories: LiveData<List<HistorySchema>> = repository.allHistories.asLiveData()

    fun insert(history: HistorySchema) {
        repository.insert(history)
    }
}

class HistoryViewModelFactory(private val repository: HistoryRepository): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
            return HistoryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown viewmodel class")
    }
}