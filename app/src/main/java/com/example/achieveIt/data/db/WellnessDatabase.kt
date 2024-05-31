package com.example.achieveIt.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.achieveIt.data.dao.WellnessDao
import com.example.achieveIt.data.entities.WellnessEntity

@Database(entities = [WellnessEntity::class],
    version = 1,
    exportSchema = false)
abstract class WellnessDatabase : RoomDatabase() {
    abstract fun wellnessDao() : WellnessDao
}