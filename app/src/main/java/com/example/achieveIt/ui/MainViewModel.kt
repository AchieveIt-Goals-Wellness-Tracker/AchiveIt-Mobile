package com.example.achieveIt.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.achieveIt.data.entities.GoalEntity
import com.example.achieveIt.data.entities.WellnessEntity
import com.example.achieveIt.data.repository.GoalsRepository
import com.example.achieveIt.data.repository.WellnessRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MainViewModel @Inject constructor(
    private val goalsRepository: GoalsRepository,
    private val wellnessRepository: WellnessRepository
) : ViewModel() {

    var goalTitleState by mutableStateOf("")
        private set
    fun updateGoalTitle(input: String) {
        goalTitleState = input
    }

    var goalDescriptionState by mutableStateOf("")
        private set
    fun updateGoalDescription(input: String) {
        goalDescriptionState = input
    }

    var goalDate by mutableStateOf("")
        private set
    fun updateGoalDate(input: String) {
        goalDate = input
    }

    var morningEmotional by mutableIntStateOf(0)
        private set
    fun updateMorningEmotional(value: Int) {
        morningEmotional = value
    }

    var eveningEmotional by mutableIntStateOf(0)
        private set
    fun updateEveningEmotional(value: Int) {
        eveningEmotional = value
    }

    var eveningActivity by mutableIntStateOf(0)
        private set
    fun updateEveningActivity(value: Int) {
        eveningActivity = value
    }

    var eveningProductivity by mutableIntStateOf(0)
        private set
    fun updateEveningProductivity(value: Int) {
        eveningProductivity = value
    }

    var currentDate by mutableStateOf("")
        private set
    fun updateCurrentDate(value: String) {
        currentDate = value
    }

    var showAddGoalDialogState by mutableStateOf(false)
    var showAddWellnessDialogState by mutableStateOf(false)

    private var _allGoals = MutableStateFlow<List<GoalEntity>>(emptyList())
    private var _allWellness = MutableStateFlow<List<WellnessEntity>>(emptyList())
    val goals: StateFlow<List<GoalEntity>>
        get() = _allGoals.asStateFlow()
    val wellness: StateFlow<List<WellnessEntity>>
        get() = _allWellness.asStateFlow()

    init {
        viewModelScope.launch {
            goalsRepository.getAllGoals().collect {
                _allGoals.value = it
            }
        }
        viewModelScope.launch {
            wellnessRepository.getAllWellness().collect {
                _allWellness.value = it
            }
        }
    }

    // Функции для Goals
    fun getGoal(id: Int) = viewModelScope.launch { goalsRepository.getGoal(id) }

    fun insertGoal(goal: GoalEntity) = viewModelScope.launch { goalsRepository.insert(goal) }

    fun updateGoal(goal: GoalEntity) = viewModelScope.launch { goalsRepository.update(goal) }

    fun deleteGoal(goal: GoalEntity) = viewModelScope.launch { goalsRepository.delete(goal) }

    // Функции для Wellness

    fun getWellness(currentDate: String) = viewModelScope.launch { wellnessRepository.getWellness(currentDate) }

    fun upsertWellness(wellness: WellnessEntity) = viewModelScope.launch { wellnessRepository.upsert(wellness) }

    fun deleteWellness(wellness: WellnessEntity) = viewModelScope.launch { wellnessRepository.delete(wellness) }

    fun clearGoalDialogData() {
        goalTitleState = ""
        goalDescriptionState = ""
        goalDate = ""
    }

    fun clearWellnessDialogData() {
        morningEmotional = 0
        eveningActivity = 0
        eveningEmotional = 0
        eveningProductivity = 0
        currentDate = ""
    }

    fun setWellnessDataToViewModel(item: WellnessEntity) {
        morningEmotional = item.morningEmotional
        eveningActivity = item.eveningActivity
        eveningEmotional = item.eveningEmotional
        eveningProductivity = item.eveningProductivity
        currentDate = item.currentDate
    }
}