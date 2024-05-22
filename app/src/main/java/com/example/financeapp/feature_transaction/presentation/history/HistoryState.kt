package com.example.financeapp.feature_transaction.presentation.history

import com.example.financeapp.feature_transaction.domain.model.Transaction

data class HistoryState(
    val transactions: List<Transaction> = emptyList()
)