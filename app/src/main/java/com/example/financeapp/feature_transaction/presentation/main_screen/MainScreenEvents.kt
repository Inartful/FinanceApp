package com.example.financeapp.feature_transaction.presentation.main_screen

import com.example.financeapp.feature_transaction.domain.model.Account

sealed class MainScreenEvents {
    data class ChangeAccount(val account: Account): MainScreenEvents()
}