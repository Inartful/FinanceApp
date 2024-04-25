package com.example.financeapp.domain.util

sealed class TransactionType {
    object Income: TransactionType()
    object Outcome: TransactionType()
}