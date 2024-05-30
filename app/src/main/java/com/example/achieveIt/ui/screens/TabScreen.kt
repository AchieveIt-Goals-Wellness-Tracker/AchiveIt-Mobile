package com.example.achieveIt.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.achieveIt.ui.MainViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun TabScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    var tabIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    val tabs = listOf("Состояние", "Цели")

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = { Text(text = title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }
        when (tabIndex) {
            0 -> HealthyStatus(viewModel = viewModel)
            else -> Goals(viewModel = viewModel)
        }
    }
}