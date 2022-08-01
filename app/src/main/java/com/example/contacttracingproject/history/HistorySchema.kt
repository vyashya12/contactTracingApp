package com.example.contacttracingproject.history

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_table")
data class HistorySchema(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    @NonNull
    @ColumnInfo(name="fullname")
    val fullname: String,

    @NonNull
    @ColumnInfo(name="name")
    val name: String,

    @NonNull
    @ColumnInfo(name="location")
    val location: String,

    @NonNull
    @ColumnInfo(name="date")
    val date: String,

    @NonNull
    @ColumnInfo(name="time")
    val time: String
)