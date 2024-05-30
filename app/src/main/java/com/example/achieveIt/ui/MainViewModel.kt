package com.example.achieveIt.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.achieveIt.data.entities.GoalEntity
import com.example.achieveIt.data.repository.GoalsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MainViewModel @Inject constructor(
    private val goalsRepository: GoalsRepository
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

    var showDialogState by mutableStateOf(false)

    private var _allGoals = MutableStateFlow<List<GoalEntity>>(emptyList())
    val goals: StateFlow<List<GoalEntity>>
        get() = _allGoals.asStateFlow()

    init {
        viewModelScope.launch {
            goalsRepository.getAllGoals().collect {
                _allGoals.value = it
            }
        }

    }

    fun getGoal(id: Int) = viewModelScope.launch { goalsRepository.getGoal(id) }

    fun insert(goal: GoalEntity) = viewModelScope.launch { goalsRepository.insert(goal) }

    fun update(goal: GoalEntity) = viewModelScope.launch { goalsRepository.update(goal) }

    fun delete(goal: GoalEntity) = viewModelScope.launch { goalsRepository.delete(goal) }

    fun clearData() {
        goalTitleState = ""
        goalDescriptionState = ""
        goalDate = ""
    }
}