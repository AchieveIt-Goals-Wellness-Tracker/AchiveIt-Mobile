package com.example.achieveIt.ui

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.achieveIt.data.entities.GoalEntity
import com.example.achieveIt.data.repository.GoalsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.Locale.filter
import javax.inject.Inject
@HiltViewModel
class MainViewModel @Inject constructor(
    private val goalsRepository: GoalsRepository
) : ViewModel() {

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
}