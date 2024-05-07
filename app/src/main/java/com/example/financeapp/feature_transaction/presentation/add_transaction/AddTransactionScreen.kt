package com.example.financeapp.feature_transaction.presentation.add_transaction

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.financeapp.feature_transaction.presentation.add_transaction.components.EnterAmount
import com.example.financeapp.feature_transaction.presentation.add_transaction.components.EnterName
import com.example.financeapp.feature_transaction.presentation.add_transaction.components.SelectAccount
import com.example.financeapp.feature_transaction.presentation.add_transaction.components.SelectCategory
import com.example.financeapp.feature_transaction.presentation.add_transaction.components.SelectType
import com.example.financeapp.feature_transaction.presentation.util.Screen

@Composable
fun AddTransactionScreen(
    viewModel: AddTransactionViewModel = hiltViewModel(),
    navController: NavController,
    transactionId: Int
) {
    val state = viewModel.state

    LaunchedEffect(key1 = Unit) {
        if (transactionId > 0) {
            viewModel.onEvent(AddTransactionEvents.LoadTransaction(transactionId))
        }
    }

    Scaffold(
        floatingActionButton = {
           Column(
               horizontalAlignment = Alignment.CenterHorizontally
           ) {
               FloatingActionButton(
                   onClick = {
                       viewModel.onEvent(AddTransactionEvents.DeleteTransaction)
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
                       viewModel.onEvent(AddTransactionEvents.SaveTransaction)
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
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            EnterName()

            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                SelectType()
                SelectCategory()
                SelectAccount(
                    addOnClick = {accountId ->
                        navController.navigate(Screen.AddAccount.route + "/${accountId}")
                    }
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            EnterAmount()
        }
    }
}