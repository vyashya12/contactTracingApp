package com.example.contacttracingproject.history

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(history: HistorySchema)

    @Query("SELECT * FROM history_table ORDER BY id DESC")
    fun getAllHistories(): Flow<List<HistorySchema>>
}