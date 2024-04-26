package com.example.financeapp.feature_transaction.domain.util

sealed class CurrencyType {
    object KZT: CurrencyType()
    object USS: CurrencyType()
    object EUR: CurrencyType()
    object RUR: CurrencyType()
}