package com.example.achieveIt.ui.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.W400
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.achieveIt.data.entities.GoalEntity
import com.example.achieveIt.ui.MainViewModel
import com.example.achieveIt.ui.theme.HealthyMeTheme
import com.example.achieveIt.ui.theme.Roboto

@Composable
fun GoalCard(
    goalEntity: GoalEntity,
    onChecked: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth().padding(12.dp)
        ) {
            Checkbox(
                checked = goalEntity.isCompleted,
                onCheckedChange = {
                    onChecked()
                },
                modifier = Modifier.weight(5f)
            )
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.weight(16f)
            ) {
                Text(
                    text = goalEntity.title,
                    fontFamily = Roboto,
                    fontSize = 16.sp,
                    fontWeight = W400,
                    lineHeight = 24.sp,
                    color = Color(0xFF1D1B20),
                    maxLines = 2
                )
                Text(
                    text = goalEntity.description,
                    fontFamily = Roboto,
                    fontSize = 14.sp,
                    fontWeight = W400,
                    lineHeight = 20.sp,
                    color = Color(0xFF49454F),
                    minLines = 2,
                    maxLines = 3
                )
            }
            IconButton(
                onClick = { onDelete() },
                modifier = Modifier.weight(5f)
            ) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = null)
            }
        }
    }
}