package com.example.financeapp.feature_transaction.presentation.add_transaction

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.financeapp.feature_transaction.presentation.add_transaction.components.EnterName
import com.example.financeapp.feature_transaction.presentation.add_transaction.components.SelectCategory
import com.example.financeapp.feature_transaction.presentation.add_transaction.components.SelectType

@Composable
fun AddTransactionScreen(
    viewModel: AddTransactionViewModel = hiltViewModel(),
    navController: NavController,
    transactionId: Int
) {
    val state = viewModel.state

    LaunchedEffect(key1 = Unit) {
        if (transactionId > 0) {
            viewModel.loadTransactionById(transactionId)
        }
    }
    Scaffold {it ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Yellow)
                .padding(16.dp)
        ) {
            EnterName()

            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.background)
            ) {
                SelectType()
                SelectCategory()
            }
        }
    }
}