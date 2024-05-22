package com.example.financeapp.feature_transaction.presentation.main_screen

import com.example.financeapp.feature_transaction.domain.model.Account
import com.example.financeapp.feature_transaction.domain.model.Transaction

data class MainScreenState(
    val transactions: List<Transaction> = emptyList(),
    val accounts: List<Account> = emptyList(),
    val accountId: Int = -1,
    val expenses: Int = 0
)
