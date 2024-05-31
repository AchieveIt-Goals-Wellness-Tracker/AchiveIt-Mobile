package com.example.achieveIt.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.achieveIt.data.dao.GoalDao
import com.example.achieveIt.data.entities.GoalEntity

@Database(entities = [GoalEntity::class], version = 1, exportSchema = false)
abstract class GoalsDatabase : RoomDatabase(){
    abstract fun goalDao(): GoalDao
}