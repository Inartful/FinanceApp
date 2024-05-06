package com.example.financeapp.feature_transaction.presentation.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.financeapp.feature_transaction.presentation.menu.components.AccountsList
import com.example.financeapp.feature_transaction.presentation.menu.components.TransactionsList
import com.example.financeapp.feature_transaction.presentation.util.Screen

@Composable
fun MenuScreen(
    viewModel: MenuViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screen.AddTransaction.route + "/-1") },
                containerColor = MaterialTheme.colorScheme.tertiary,
                shape = CircleShape
                ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Add",
                    tint = MaterialTheme.colorScheme.primary)
            }
        }
    ) {it ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Yellow)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            AccountsList(
                accounts = state.value.accounts,
                onClick = { account ->
                    viewModel.onEvent(MenuEvents.ChangeAccount(account))
                }
            )
            Spacer(modifier = Modifier.height(14.dp))
            TransactionsList(
                transactions = state.value.transactions,
                historyOnClick = {},
                transactionOnClick = {transactionId ->
                    navController.navigate(Screen.AddTransaction.route + "/$transactionId")
                }
            )
        }
    }
}