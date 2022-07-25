package com.example.contacttracingproject

import android.app.Application
import com.example.contacttracingproject.data.UserDatabase
import com.example.contacttracingproject.data.UserRepository

class BaseApplication: Application() {
    val database: UserDatabase by lazy {UserDatabase.getInstance(this)}
    val repository: UserRepository by lazy {UserRepository(database.userDAO())}
}