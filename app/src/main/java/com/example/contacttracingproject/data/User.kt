package com.example.contacttracingproject.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class User (
        @SerializedName("id")
        val id: Int = 0,

        @SerializedName("fullname")
        var fullName: String,

        @SerializedName("nric")
        var nric: String,

        @SerializedName("password")
        var password: String
)