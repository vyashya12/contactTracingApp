package com.example.contacttracingproject.history

import kotlinx.coroutines.flow.Flow

class HistoryRepository(private val historyDao: HistoryDao) {
    val allHistories: Flow<List<History>> = historyDao.getAllHistories()

    fun insert(history: History) {
        historyDao.insert(history)
    }
}