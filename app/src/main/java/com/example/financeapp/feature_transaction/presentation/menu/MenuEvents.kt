package com.example.financeapp.feature_transaction.presentation.menu

import com.example.financeapp.feature_transaction.domain.model.Account

sealed class MenuEvents {
    data class ChangeAccount(val account: Account): MenuEvents()
    data class ChangePosition(val index: Int): MenuEvents()
}
