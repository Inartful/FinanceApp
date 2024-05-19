package com.example.financeapp.feature_transaction.presentation.menu

sealed class MenuEvents {
    data class ChangePosition(val index: Int): MenuEvents()
}
