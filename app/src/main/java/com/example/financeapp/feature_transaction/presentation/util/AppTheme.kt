package com.example.financeapp.feature_transaction.presentation.util

sealed class AppTheme {
    object DarkTheme: AppTheme()
    object LightTheme: AppTheme()
    object AutoTheme: AppTheme()
}