package com.example.financeapp.feature_transaction.presentation.add_account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.financeapp.feature_transaction.presentation.add_transaction.AddTransactionEvents
import com.example.financeapp.feature_transaction.presentation.util.Screen

@Composable
fun AddAccountScreen(
    viewModel: AddAccountViewModel = hiltViewModel(),
    navController: NavController,
    accountId: Int
) {
    val state = viewModel.state

    LaunchedEffect(key1 = Unit) {
        if (accountId > 0) {
            viewModel.onEvent(AddAccountEvents.LoadAccount(accountId))
        }
    }
    Scaffold(
        floatingActionButton = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                FloatingActionButton(
                    onClick = {
                        viewModel.onEvent(AddAccountEvents.DeleteAccount)
                        navController.navigate(Screen.MainScreen.route) },
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    shape = CircleShape,
                    modifier = Modifier.size(55.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Delete,
                        contentDescription = "Delete",
                        tint = MaterialTheme.colorScheme.onPrimary)
                }
                Spacer(modifier = Modifier.height(10.dp))
                FloatingActionButton(
                    onClick = {
                        viewModel.onEvent(AddAccountEvents.SaveAccount)
                        navController.navigate(Screen.MainScreen.route)
                    },
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    shape = CircleShape,
                    modifier = Modifier.size(60.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Done,
                        contentDescription = "Add",
                        tint = MaterialTheme.colorScheme.primary)
                }
            }
        }
    ) {it ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = state.value.name,
                onValueChange = {
                    viewModel.onEvent(AddAccountEvents.ChangeName(it))
                },
                placeholder = {
                    if (state.value.name.isBlank()) {
                        Text(text = "Enter name",
                            fontSize = 20.sp)
                    }
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 1.dp),
                colors = TextFieldDefaults.colors(
                    cursorColor = MaterialTheme.colorScheme.primary,
                    focusedTextColor = MaterialTheme.colorScheme.primary,
                    disabledContainerColor = MaterialTheme.colorScheme.surface,
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface
                ),
                textStyle = TextStyle(
                    fontSize = 20.sp
                )
            )
            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                value =
                if(state.value.amount == null) { "" }
                else { state.value.amount.toString() },
                onValueChange = {
                    viewModel.onEvent(AddAccountEvents.ChangeAmount(
                        if (it == "," || it == ".") {
                            null
                        } else if (it.isNotBlank()) {
                            it.toInt()
                        } else {
                            null
                        }
                    ))
                },
                placeholder = {
                    if (state.value.amount == null) {
                        Text(text = "Enter amount",
                            fontSize = 20.sp)
                    }
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 1.dp),
                colors = TextFieldDefaults.colors(
                    cursorColor = MaterialTheme.colorScheme.primary,
                    focusedTextColor = MaterialTheme.colorScheme.primary,
                    disabledContainerColor = MaterialTheme.colorScheme.surface,
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface
                ),
                textStyle = TextStyle(
                    fontSize = 20.sp
                )
            )
        }
    }
}