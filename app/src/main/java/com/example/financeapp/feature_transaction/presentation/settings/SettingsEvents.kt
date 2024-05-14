package com.example.financeapp.feature_transaction.presentation.settings

import com.example.financeapp.feature_transaction.presentation.util.AppTheme

sealed class SettingsEvents {
    data class ChangeTheme(val theme: AppTheme): SettingsEvents()
    data class ChangeChosenTheme(val theme: AppTheme): SettingsEvents()
}