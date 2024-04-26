package com.example.financeapp.feature_transaction.domain.util

sealed class TransactionType {
    object Income: TransactionType()
    object Outcome: TransactionType()
}