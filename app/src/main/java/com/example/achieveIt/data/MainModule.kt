package com.example.achieveIt.data

import android.content.Context
import androidx.room.Room
import com.example.achieveIt.data.dao.GoalDao
import com.example.achieveIt.data.dao.WellnessDao
import com.example.achieveIt.data.db.GoalsDatabase
import com.example.achieveIt.data.db.WellnessDatabase
import com.example.achieveIt.data.repository.GoalsRepository
import com.example.achieveIt.data.repository.WellnessRepository
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
    fun provideGoalsDatabase(@ApplicationContext context: Context): GoalsDatabase =
        Room.databaseBuilder(context, GoalsDatabase::class.java, "goals_database")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideGoalDao(goalsDatabase: GoalsDatabase): GoalDao = goalsDatabase.goalDao()

    @Provides
    fun provideGoalsRepository(goalDao: GoalDao): GoalsRepository = GoalsRepository(goalDao)

    @Provides
    @Singleton
    fun provideWellnessDatabase(@ApplicationContext context: Context): WellnessDatabase =
        Room.databaseBuilder(context, WellnessDatabase::class.java, "wellness_database")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideWellnessDao(wellnessDatabase: WellnessDatabase): WellnessDao = wellnessDatabase.wellnessDao()

    @Provides
    fun provideWellnessRepository(wellnessDao: WellnessDao): WellnessRepository = WellnessRepository(wellnessDao)
}