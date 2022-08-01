package com.example.contacttracingproject

import android.app.Application
import com.example.contacttracingproject.data.UserDatabase
import com.example.contacttracingproject.data.UserRepository
import com.example.contacttracingproject.history.HistoryDatabase
import com.example.contacttracingproject.history.HistoryRepository

class BaseApplication: Application() {
    val database: UserDatabase by lazy {UserDatabase.getInstance(this)}
    val repository: UserRepository by lazy {UserRepository(database.userDAO())}
    val historyDatabase: HistoryDatabase by lazy { HistoryDatabase.getDatabase(this)}
    val historyRepository: HistoryRepository by lazy { HistoryRepository(historyDatabase.historyDao()) }
}