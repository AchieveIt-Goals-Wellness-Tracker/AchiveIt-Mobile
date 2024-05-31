package com.example.achieveIt.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wellness")
class WellnessEntity (
    @PrimaryKey val currentDate: String,
    val morningEmotional: Int,
    val eveningEmotional: Int,
    val eveningActivity: Int,
    val eveningProductivity: Int
)