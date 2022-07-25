package com.example.contacttracingproject.data

import kotlinx.coroutines.flow.Flow

class UserRepository(private val dao: UserDAO) {

    var userList: Flow<List<User>> = dao.getAllUsers()
    fun getUsers(): Flow<List<User>> {
        return userList
    }

    suspend fun register(user: User) {
        dao.register(user)
    }

    suspend fun login(fullName: String): User {
        return dao.login(fullName)
    }
}