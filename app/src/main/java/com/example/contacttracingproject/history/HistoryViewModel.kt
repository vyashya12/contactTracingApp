package com.example.contacttracingproject.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.Flow

class HistoryViewModel(private val repository: HistoryRepository) : ViewModel() {
    val allHistories: Flow<List<History>> = repository.allHistories

    fun insert(history: History) {
        repository.insert(history)
    }
}

class HistoryListViewModelFactory(private val repository: HistoryRepository): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
            return HistoryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown viewmodel class")
    }
}