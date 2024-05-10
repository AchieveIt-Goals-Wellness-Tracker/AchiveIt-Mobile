package com.example.achieveIt.data.repository

import com.example.achieveIt.data.dao.GoalDao
import com.example.achieveIt.data.entities.GoalEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GoalsRepository @Inject constructor (private val goalDao: GoalDao) {
    fun getAllGoals(): Flow<List<GoalEntity>> = goalDao.getAllGoals()

    fun getGoal(id: Int) : Flow<GoalEntity> = goalDao.getGoal(id)
    suspend fun insert(goal: GoalEntity){
        goalDao.insert(goal)
    }

    suspend fun update(goal: GoalEntity) {
        goalDao.update(goal)
    }

    suspend fun delete(goal: GoalEntity) {
        goalDao.delete(goal)
    }
}