package com.example.financeapp.feature_transaction.presentation.settings

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.financeapp.feature_transaction.presentation.util.AppTheme

class SettingsViewModel: ViewModel()  {
    private val _state = mutableStateOf(SettingsState(
        theme = AppTheme.AutoTheme,
        chooseTheme = AppTheme.AutoTheme)
    )
    val state: State<SettingsState> = _state

    fun onEvent(event: SettingsEvents) {
        when(event) {
            is SettingsEvents.ChangeTheme -> {
                _state.value = state.value.copy(
                    theme = event.theme
                )
            }
            is SettingsEvents.ChangeChosenTheme -> {
                _state.value = state.value.copy(
                    chooseTheme = event.theme
                )
            }
        }
    }
}