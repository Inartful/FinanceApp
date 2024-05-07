package com.example.financeapp.feature_transaction.presentation.menu

import com.example.financeapp.feature_transaction.domain.model.Account
import com.example.financeapp.feature_transaction.domain.model.Transaction

data class MenuState(
    val transactions: List<Transaction> = emptyList(),
    val accounts: List<Account> = emptyList(),
    val selected: Int = 0
)