package com.example.contacttracingproject.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDAO {
    @Insert
    fun register(user: User)

    @Query("SELECT * FROM userTable WHERE fullName =:fullName")
    fun login(fullName: String): User

    @Query("SELECT * FROM userTable ORDER BY id DESC")
    fun getAllUsers(): Flow<List<User>>
}