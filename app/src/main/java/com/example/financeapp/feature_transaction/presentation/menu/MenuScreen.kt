package com.example.financeapp.feature_transaction.presentation.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.financeapp.feature_transaction.presentation.menu.components.AccountsList
import com.example.financeapp.feature_transaction.presentation.menu.components.TransactionsList

@Composable
fun MenuScreen(
    viewModel: MenuViewModel = hiltViewModel(),
//    navController: NavController
) {
    val state = viewModel.state

    Scaffold {it ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Yellow)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            AccountsList(accounts = state.value.accounts)
            TransactionsList(
                transactions = state.value.transactions,
                historyOnClick = {}
            )
        }
    }
}