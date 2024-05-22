package com.example.financeapp.feature_transaction.presentation.history

sealed class HistoryEvents {
    data class LoadTransactions(val accountId: Int): HistoryEvents()
}