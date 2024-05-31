package com.example.achieveIt.ui.dialogs

import android.icu.util.Calendar
import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight.Companion.W400
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.achieveIt.toStringDate
import com.example.achieveIt.ui.MainViewModel
import com.example.achieveIt.ui.theme.Roboto
import java.util.Date

@Composable
fun AddGoalDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val calendar = Calendar.getInstance()

    val year: Int = calendar.get(Calendar.YEAR)
    val month: Int = calendar.get(Calendar.MONTH)
    val day: Int = calendar.get(Calendar.DAY_OF_MONTH)

    calendar.time = Date()

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, currentYear: Int, currentMonth: Int, currentDay: Int ->
            viewModel.updateGoalDate(toStringDate(currentYear,currentMonth,currentDay))
        }, year, month, day
    )


    val focusManager = LocalFocusManager.current

    Dialog(
        onDismissRequest = { onDismissRequest() },
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 24.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    Text(
                        text = "Новая цель",
                        fontFamily = Roboto,
                        fontWeight = W400,
                        fontSize = 24.sp,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(
                        onClick = { onDismissRequest() },
                        modifier = Modifier.size(34.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = null,
                        )
                    }
                }
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = viewModel.goalTitleState,
                        onValueChange = { viewModel.updateGoalTitle(it) },
                        label = { Text("Заголовок") },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                focusManager.moveFocus(FocusDirection.Down)
                            }
                        )
                    )
                    Spacer(Modifier.height(15.dp))
                    // todo: move focus on textfield
                    OutlinedTextField(
                        value = viewModel.goalDescriptionState,
                        onValueChange = { viewModel.updateGoalDescription(it) },
                        label = { Text("Описание") },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                focusManager.moveFocus(FocusDirection.Down)
                            }
                        )
                    )
                    Spacer(Modifier.height(14.dp))
                    TextButton(onClick = { datePickerDialog.show() }) {
                        Text(
                            text = "Выбрать дату"
                        )
                    }
                    Spacer(Modifier.height(5.dp))
                    Text(
                        text = "Дата: ${viewModel.goalDate}",
                        modifier = Modifier.padding(start = 8.dp),
                        fontFamily = Roboto,
                        fontWeight = W400,
                        fontSize = 14.sp,
                        lineHeight = 16.sp
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
                            enabled = viewModel.goalTitleState.isNotEmpty() &&
                                    viewModel.goalDescriptionState.isNotEmpty() &&
                                    viewModel.goalDate.isNotEmpty(),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "Добавить",
                                fontFamily = Roboto,
                                fontWeight = W500,
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
                                fontWeight = W500,
                                fontSize = 14.sp,
                            )
                        }
                    }
                }

            }
        }
    }
}

@Preview
@Composable
private fun DialogPreview() {
    Column(
        modifier = Modifier.background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Text(
                text = "Новая цель",
                fontFamily = Roboto,
                fontWeight = W400,
                fontSize = 24.sp,
                modifier = Modifier.wrapContentHeight()
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { },
                modifier = Modifier.size(36.dp)) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = null,
                )
            }
        }
    }
}
