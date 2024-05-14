package com.example.financeapp.feature_transaction.presentation.settings

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.financeapp.R
import com.example.financeapp.feature_transaction.presentation.util.AppTheme
import com.example.financeapp.feature_transaction.presentation.util.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: SettingsViewModel = viewModel()
) {
    val state = viewModel.state

    var goBackFunctions: () -> Unit = {}

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.settings))
                },
                navigationIcon = {
                    IconButton(onClick = {
                        goBackFunctions()
                        navController.navigate(Screen.MainScreen.route)
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.go_back),
                        )
                    }
                }
            )
        },
        modifier = Modifier
            .fillMaxSize()
    ) {padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(padding)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .selectableGroup()
            ) {
                Text(
                    text = stringResource(R.string.choose_theme),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = state.value.chooseTheme == AppTheme.AutoTheme,
                        onClick = {
                            goBackFunctions = {
                                viewModel.onEvent(
                                SettingsEvents
                                    .ChangeTheme(AppTheme.AutoTheme)
                                )
                            }
                            viewModel.onEvent(
                                SettingsEvents
                                    .ChangeChosenTheme(AppTheme.AutoTheme))
                        },
                        modifier = Modifier.padding(end = 8.dp),
                        colors = RadioButtonColors(
                            selectedColor = MaterialTheme.colorScheme.primary,
                            unselectedColor = MaterialTheme.colorScheme.tertiary,
                            disabledSelectedColor = Color.Transparent,
                            disabledUnselectedColor = Color.Transparent
                        )
                    )
                    Text(
                        text = stringResource(R.string.auto),
                        fontSize = 20.sp
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = state.value.chooseTheme == AppTheme.LightTheme,
                        onClick = {
                            goBackFunctions = {
                                viewModel.onEvent(
                                    SettingsEvents
                                        .ChangeTheme(AppTheme.LightTheme)
                                )
                            }
                            viewModel.onEvent(
                                SettingsEvents
                                    .ChangeChosenTheme(AppTheme.LightTheme))
                        },
                        modifier = Modifier.padding(end = 8.dp),
                        colors = RadioButtonColors(
                            selectedColor = MaterialTheme.colorScheme.primary,
                            unselectedColor = MaterialTheme.colorScheme.tertiary,
                            disabledSelectedColor = Color.Transparent,
                            disabledUnselectedColor = Color.Transparent
                        )
                    )
                    Text(
                        text = stringResource(R.string.light),
                        fontSize = 20.sp
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = state.value.chooseTheme == AppTheme.DarkTheme,
                        onClick = {
                            goBackFunctions = {
                                viewModel.onEvent(
                                    SettingsEvents
                                        .ChangeTheme(AppTheme.DarkTheme)
                                )
                            }
                            viewModel.onEvent(
                                SettingsEvents
                                    .ChangeChosenTheme(AppTheme.DarkTheme))
                        },
                        modifier = Modifier.padding(end = 8.dp),
                        colors = RadioButtonColors(
                            selectedColor = MaterialTheme.colorScheme.primary,
                            unselectedColor = MaterialTheme.colorScheme.tertiary,
                            disabledSelectedColor = Color.Transparent,
                            disabledUnselectedColor = Color.Transparent
                        )
                    )
                    Text(
                        text = stringResource(R.string.dark),
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}
