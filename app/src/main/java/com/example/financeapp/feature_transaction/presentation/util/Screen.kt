package com.example.financeapp.feature_transaction.presentation.util

sealed class Screen(val route: String) {
    object AddAccount: Screen("add_account")
    object MainScreen : Screen("main_screen")
    object AddTransaction : Screen("add_transaction")
    object Settings: Screen("settings")
    object History: Screen("history")
}