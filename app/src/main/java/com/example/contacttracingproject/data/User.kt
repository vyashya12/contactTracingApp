package com.example.contacttracingproject.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userTable")
data class User (
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,

        @ColumnInfo(name = "fullName")
        val fullName: String,

        @ColumnInfo(name = "nric")
        val nric: Int,

        @ColumnInfo(name = "password")
        val password: String
        )