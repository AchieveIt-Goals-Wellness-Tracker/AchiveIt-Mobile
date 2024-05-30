package com.example.achieveIt.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.achieveIt.data.dao.GoalDao
import com.example.achieveIt.data.entities.GoalEntity
import javax.inject.Inject

@Database(entities = [GoalEntity::class], version = 1, exportSchema = false)
abstract class GoalsDatabase : RoomDatabase(){
    abstract fun goalDao(): GoalDao

//    companion object {
//        @Volatile
//        private var INSTANCE: GoalsDatabase? = null
//
//        fun getDatabase(context: Context): GoalsDatabase {
//            return INSTANCE ?: synchronized(this) {
//                Room.databaseBuilder(context, GoalsDatabase::class.java, "goals_database")
//                    .build()
//                    .also { INSTANCE = it }
//            }
//        }
//
//    }
}