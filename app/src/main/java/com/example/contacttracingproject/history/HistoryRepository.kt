package com.example.contacttracingproject.history

import kotlinx.coroutines.flow.Flow

class HistoryRepository(private val historyDao: HistoryDao) {
    val allHistories: Flow<List<HistorySchema>> = historyDao.getAllHistories()

    fun insert(history: HistorySchema) {
        historyDao.insert(history)
    }
}