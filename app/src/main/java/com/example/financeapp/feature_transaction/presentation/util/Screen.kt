package com.example.financeapp.feature_transaction.presentation.util

sealed class Screen(val route: String) {
    object MainScreen : Screen("main_screen")
    object AddTransaction : Screen("add_transaction")
}