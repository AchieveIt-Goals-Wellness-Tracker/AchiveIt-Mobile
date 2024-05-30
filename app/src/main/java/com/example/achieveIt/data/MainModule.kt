package com.example.achieveIt.data

import android.content.Context
import androidx.room.Room
import com.example.achieveIt.data.dao.GoalDao
import com.example.achieveIt.data.db.GoalsDatabase
import com.example.achieveIt.data.repository.GoalsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): GoalsDatabase =
        Room.databaseBuilder(context, GoalsDatabase::class.java, "goals_database")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideItemDao(goalsDatabase: GoalsDatabase): GoalDao = goalsDatabase.goalDao()

    @Provides
    fun provideGoalsRepository(goalDao: GoalDao): GoalsRepository = GoalsRepository(goalDao)
}