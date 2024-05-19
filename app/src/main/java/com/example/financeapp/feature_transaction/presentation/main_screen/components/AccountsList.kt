package com.example.financeapp.feature_transaction.presentation.main_screen.components

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.financeapp.feature_transaction.domain.model.Account

@Composable
fun AccountsList(
    accounts: List<Account>,
    modifier: Modifier = Modifier,
    onClick: (account: Account) -> Unit
) {
    LazyRow {
        items(accounts) {account ->
            AccountItem(
                account = account,
                onClick = onClick
            )
        }
    }
}