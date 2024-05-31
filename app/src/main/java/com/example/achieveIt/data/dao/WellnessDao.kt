package com.example.achieveIt.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.achieveIt.data.entities.WellnessEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WellnessDao {

    @Upsert
    suspend fun upsert(wellness: WellnessEntity)

    @Delete
    suspend fun delete(wellness: WellnessEntity)

    @Query("SELECT * FROM wellness WHERE currentDate = :currentDate")
    fun getWellness(currentDate: String): Flow<WellnessEntity>

    @Query("SELECT * FROM wellness ORDER BY currentDate ASC")
    fun getAllWellness(): Flow<List<WellnessEntity>>
}