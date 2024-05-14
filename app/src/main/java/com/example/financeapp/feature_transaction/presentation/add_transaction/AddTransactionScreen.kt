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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.financeapp.R
import com.example.financeapp.feature_transaction.presentation.add_transaction.components.EnterAmount
import com.example.financeapp.feature_transaction.presentation.add_transaction.components.EnterName
import com.example.financeapp.feature_transaction.presentation.add_transaction.components.SelectAccount
import com.example.financeapp.feature_transaction.presentation.add_transaction.components.SelectCategory
import com.example.financeapp.feature_transaction.presentation.add_transaction.components.SelectType
import com.example.financeapp.feature_transaction.presentation.util.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTransactionScreen(
    viewModel: AddTransactionViewModel = hiltViewModel(),
    navController: NavController,
    transactionId: Int
) {
    val state = viewModel.state
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    LaunchedEffect(key1 = Unit) {
        if (transactionId > 0) {
            viewModel.onEvent(AddTransactionEvents.LoadTransaction(transactionId))
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.create_transaction))
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(Screen.MainScreen.route) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.go_back),
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
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
                       contentDescription = stringResource(R.string.delete),
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
                       contentDescription = stringResource(R.string.add),
                       tint = MaterialTheme.colorScheme.primary)
               }
           }
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