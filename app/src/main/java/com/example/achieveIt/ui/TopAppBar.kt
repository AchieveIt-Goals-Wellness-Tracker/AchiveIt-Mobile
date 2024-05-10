package com.example.achieveIt.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.achieveIt.R
import com.example.achieveIt.ui.theme.HealthyMeTheme
import com.example.achieveIt.ui.theme.Roboto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            titleContentColor = Color(0xFF1D1B20),
            actionIconContentColor = Color.Black
        ),
        title = {
            Text(
                text = stringResource(id = R.string.top_app_bar_name),
                textAlign = TextAlign.Center,
                fontSize = 22.sp,
                lineHeight = 28.sp,
                fontFamily = Roboto,
                modifier = Modifier.fillMaxWidth()
            )
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "Basket button"
                )
            }
        },
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun TopBarPreview() {
    HealthyMeTheme {
        Scaffold (
            topBar = {
                MainTopAppBar()
            }
        ) { innerPadding ->

        }
    }
}