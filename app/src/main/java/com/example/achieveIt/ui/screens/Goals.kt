package com.example.achieveIt.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.achieveIt.data.entities.GoalEntity
import com.example.achieveIt.ui.dialogs.AddGoalDialog
import com.example.achieveIt.ui.MainViewModel
import com.example.achieveIt.ui.cards.GoalCard
import com.example.achieveIt.ui.theme.Roboto

@Composable
fun Goals(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    val goals = viewModel.goals.collectAsState().value
    val completedGoals = goals.filter { it.isCompleted }
    val uncompletedGoals = goals.filter { !it.isCompleted }

    var isCompletedGoalsSelected by rememberSaveable {
        mutableStateOf(true)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(
                onClick = { isCompletedGoalsSelected = true },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = if (isCompletedGoalsSelected) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.background,
                    contentColor = if (isCompletedGoalsSelected) Color.White else Color.Black
                ),
                modifier = Modifier
                    .width(80.dp)
                    .padding(horizontal = 12.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = null
                )
            }
            IconButton(
                onClick = { isCompletedGoalsSelected = false },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = if (!isCompletedGoalsSelected) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.background,
                    contentColor = if (!isCompletedGoalsSelected) Color.White else Color.Black
                ),
                modifier = Modifier
                    .width(80.dp)
                    .padding(horizontal = 12.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = null
                )
            }
        }

        if (isCompletedGoalsSelected) {
            LazyColumn(
                contentPadding = PaddingValues(13.dp),
                verticalArrangement = Arrangement.spacedBy(13.dp),
                modifier = Modifier
                    .heightIn(
                        max =
                        (108 * uncompletedGoals.size
                                + 13 * (uncompletedGoals.size - 1)
                                + 15).dp
                    )
            ) {
                items(uncompletedGoals) { goal ->
                    GoalCard(
                        goalEntity = goal,
                        onChecked = { viewModel.updateGoal(goal.copy(isCompleted = !goal.isCompleted)) },
                        onDelete = { viewModel.deleteGoal(goal) }
                    )
                }
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(13.dp),
                verticalArrangement = Arrangement.spacedBy(13.dp),
                modifier = Modifier
                    .heightIn(
                        max =
                        (108 * completedGoals.size
                                + 13 * (completedGoals.size - 1)
                                + 15).dp
                    )
            ) {
                items(completedGoals) { goal ->
                    GoalCard(
                        goalEntity = goal,
                        onChecked = { viewModel.updateGoal(goal.copy(isCompleted = !goal.isCompleted)) },
                        onDelete = { viewModel.deleteGoal(goal) }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                viewModel.showAddGoalDialogState = viewModel.showAddGoalDialogState.not()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 13.dp, end = 13.dp, bottom = 3.dp)
        ) {
            Row(
                modifier = Modifier.padding(vertical = 10.dp)
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = null)
                Text(
                    text = "Добавить цель",
                    fontFamily = Roboto,
                    fontSize = 20.sp,
                    fontWeight = W500
                )
            }
        }

        if (viewModel.showAddGoalDialogState) {
            AddGoalDialog(
                onDismissRequest = {
                    viewModel.showAddGoalDialogState = false
                    viewModel.clearGoalDialogData()
                },
                onConfirmation = {
                    viewModel.insertGoal(
                        GoalEntity(
                            title = viewModel.goalTitleState,
                            description = viewModel.goalDescriptionState,
                            date = viewModel.goalDate
                        )
                    )
                    viewModel.showAddGoalDialogState = false
                    viewModel.clearGoalDialogData()
                },
                viewModel = viewModel
            )
        }
    }
}