package com.example.achieveIt.ui.screens

import android.icu.util.Calendar
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.achieveIt.data.entities.GoalEntity
import com.example.achieveIt.data.entities.WellnessEntity
import com.example.achieveIt.toStringDate
import com.example.achieveIt.ui.MainViewModel
import com.example.achieveIt.ui.cards.EmptyWellnessCard
import com.example.achieveIt.ui.cards.WellnessCard
import com.example.achieveIt.ui.dialogs.AddWellnessDialog
import com.example.achieveIt.ui.theme.Roboto
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import java.util.Date

@Composable
fun HealthyStatus(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    val wellness = viewModel.wellness.collectAsState().value

    val calendar = Calendar.getInstance()

    val year: Int = calendar.get(Calendar.YEAR)
    val month: Int = calendar.get(Calendar.MONTH)
    val day: Int = calendar.get(Calendar.DAY_OF_MONTH)

    calendar.time = Date()

    val containsWellness =
        wellness.find { it.currentDate == toStringDate(year, month, day) } != null

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        LazyColumn(
            contentPadding = PaddingValues(13.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.heightIn(
                max = (180 * wellness.size
                        + 16 * (wellness.size - 1)
                        + if (!containsWellness) 100 else 0
                        + 40).dp
            )
        ) {
            item {
                if (!containsWellness) {
                    EmptyWellnessCard()
                }
            }
            items(wellness) { item ->
                WellnessCard(
                    wellnessEntity = item,
                    onDelete = { viewModel.deleteWellness(item) },
                    onChange = {
                        viewModel.setWellnessDataToViewModel(item)
                        viewModel.showAddWellnessDialogState =
                            viewModel.showAddWellnessDialogState.not()
                    }
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                viewModel.showAddWellnessDialogState = viewModel.showAddWellnessDialogState.not()
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
                    text = "Добавить состояние",
                    fontFamily = Roboto,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W500
                )
            }

            if (viewModel.showAddWellnessDialogState) {
                AddWellnessDialog(
                    onDismissRequest = {
                        viewModel.showAddWellnessDialogState = false
                        viewModel.clearWellnessDialogData()
                    },
                    onConfirmation = {
                        viewModel.upsertWellness(
                            WellnessEntity(
                                currentDate = viewModel.currentDate.ifBlank { toStringDate(year, month, day) },
                                morningEmotional = viewModel.morningEmotional,
                                eveningEmotional = viewModel.eveningEmotional,
                                eveningProductivity = viewModel.eveningProductivity,
                                eveningActivity = viewModel.eveningActivity
                            )
                        )
                        viewModel.showAddWellnessDialogState = false
                        viewModel.clearWellnessDialogData()
                        Log.d("MyLog", "${viewModel.wellness.value.size}")
                    },
                    viewModel = viewModel
                )
            }
        }
    }
}