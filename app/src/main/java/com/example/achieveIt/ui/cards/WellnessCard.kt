package com.example.achieveIt.ui.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.W400
import androidx.compose.ui.text.font.FontWeight.Companion.W600
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.achieveIt.data.entities.WellnessEntity
import com.example.achieveIt.ui.theme.HealthyMeTheme
import com.example.achieveIt.ui.theme.Roboto

@Composable
fun WellnessCard(
    wellnessEntity: WellnessEntity,
    onDelete: () -> Unit,
    onChange: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(7.dp)
            ) {
                Text(
                    text = "День: ${wellnessEntity.currentDate}",
                    fontFamily = Roboto,
                    fontSize = 14.sp,
                    fontWeight = W400,
                    color = Color(0xFF49454F)
                )
                Text(
                    text = "Утреннее состояние: ${wellnessEntity.morningEmotional}",
                    fontFamily = Roboto,
                    fontSize = 18.sp,
                    fontWeight = W600,
                    color = Color(0xFF1D1B20)
                )
                Text(
                    text = "Вечернее состояние: ${wellnessEntity.eveningEmotional}",
                    fontFamily = Roboto,
                    fontSize = 18.sp,
                    fontWeight = W600,
                    color = Color(0xFF1D1B20)
                )
                Text(
                    text = "Активность: ${wellnessEntity.eveningActivity}",
                    fontFamily = Roboto,
                    fontSize = 18.sp,
                    fontWeight = W600,
                    color = Color(0xFF1D1B20)
                )
                Text(
                    text = "Продуктивность: ${wellnessEntity.eveningProductivity}",
                    fontFamily = Roboto,
                    fontSize = 18.sp,
                    fontWeight = W600,
                    color = Color(0xFF1D1B20)
                )
            }
            Spacer(Modifier.weight(1f))
            Column(
                modifier = Modifier.padding(top = 14.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                IconButton(onClick = { onDelete() }) {
                    Icon(imageVector = Icons.Filled.Delete, contentDescription = null)
                }
                IconButton(onClick = { onChange() }) {
                    Icon(imageVector = Icons.Filled.Create, contentDescription = null)
                }
            }
        }

    }
}

@Preview
@Composable
private fun WellnessCardPreview() {
    HealthyMeTheme {
        WellnessCard(wellnessEntity = WellnessEntity("", 2, 2,2,2),
            onDelete = { /*TODO*/ }, onChange = { /*TODO*/ })

    }
}