package com.example.achieveIt.ui.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.achieveIt.ui.MainViewModel
import com.example.achieveIt.ui.theme.Roboto
import kotlin.math.roundToInt

@Composable
fun AddWellnessDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current

    Dialog(
        onDismissRequest = { onDismissRequest() }
    ) {
        Card(
            modifier = modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 24.dp)
            ) {
                ValueSlider(
                    text = "Утреннее состояние",
                    value = viewModel.morningEmotional,
                    onValueChange = { viewModel.updateMorningEmotional(it) }
                )
                ValueSlider(
                    text = "Вечернее состояние",
                    value = viewModel.eveningEmotional,
                    onValueChange = { viewModel.updateEveningEmotional(it) }
                )
                ValueSlider(
                    text = "Активность",
                    value = viewModel.eveningActivity,
                    onValueChange = { viewModel.updateEveningActivity(it) }
                )
                ValueSlider(
                    text = "Продуктивность",
                    value = viewModel.eveningProductivity,
                    onValueChange = { viewModel.updateEveningProductivity(it) }
                )

                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            onConfirmation()
                        },
                        enabled = viewModel.morningEmotional!=0
                                && viewModel.eveningEmotional!=0
                                && viewModel.eveningActivity!=0
                                && viewModel.eveningProductivity!=0,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Добавить",
                            fontFamily = Roboto,
                            fontWeight = FontWeight.W500,
                            fontSize = 14.sp
                        )
                    }
                    Spacer(Modifier.width(8.dp))
                    OutlinedButton(
                        onClick = { onDismissRequest() },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Отменить",
                            fontFamily = Roboto,
                            fontWeight = FontWeight.W500,
                            fontSize = 14.sp,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ValueSlider(
    text: String,
    value: Int,
    onValueChange: (Int) -> Unit,
) {
    Text(
        text = "$text: $value",
        fontFamily = Roboto,
        fontSize = 20.sp,
        fontWeight = FontWeight.W600,
        color = Color(0xFF1D1B20)
    )
    Spacer(Modifier.height(4.dp))
    Slider(
        value = value.toFloat(),
        onValueChange = { onValueChange(it.toInt()) },
        colors = SliderDefaults.colors(
            thumbColor = MaterialTheme.colorScheme.secondary,
            activeTrackColor = MaterialTheme.colorScheme.secondary,
            inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
        ),
        steps = 10,
        valueRange = 0f..10f
    )
}