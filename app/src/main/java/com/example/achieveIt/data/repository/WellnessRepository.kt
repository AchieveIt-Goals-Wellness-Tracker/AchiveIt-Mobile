package com.example.achieveIt.data.repository

import com.example.achieveIt.data.dao.WellnessDao
import com.example.achieveIt.data.entities.WellnessEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WellnessRepository @Inject constructor(private val wellnessDao: WellnessDao) {
    fun getAllWellness(): Flow<List<WellnessEntity>> = wellnessDao.getAllWellness()

    fun getWellness(currentDate: String) = wellnessDao.getWellness(currentDate)

    suspend fun upsert(wellness: WellnessEntity) = wellnessDao.upsert(wellness)

    suspend fun delete(wellness: WellnessEntity) = wellnessDao.delete(wellness)
}