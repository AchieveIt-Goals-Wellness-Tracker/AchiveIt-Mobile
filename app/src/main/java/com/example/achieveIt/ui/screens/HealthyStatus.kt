package com.example.achieveIt.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.achieveIt.ui.MainViewModel

@Composable
fun HealthyStatus(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    // TODO:
    Column(modifier = modifier.fillMaxSize()) {
        Text(text = "Экран состояния")
    }
}