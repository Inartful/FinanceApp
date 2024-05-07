package com.example.financeapp.feature_transaction.presentation.add_account

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.feature_transaction.domain.model.Account
import com.example.financeapp.feature_transaction.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddAccountViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {

    private val _state = mutableStateOf(AddAccountState())
    val state: State<AddAccountState> = _state

    fun onEvent(event: AddAccountEvents) {
        when(event) {
            is AddAccountEvents.ChangeName -> {
                _state.value = state.value.copy(name = event.name)
            }
            is AddAccountEvents.LoadAccount -> loadAccountById(event.accountId)
            is AddAccountEvents.ChangeAmount -> {
                _state.value = state.value.copy(amount = event.amount)
            }
            AddAccountEvents.SaveAccount -> {
                viewModelScope.launch {
                    useCases.addAccount(
                        Account(
                            id = state.value.id,
                            name = state.value.name,
                            amount = state.value.amount ?: 0
                        )
                    )
                }
            }
            AddAccountEvents.DeleteAccount -> {
                viewModelScope.launch {
                    if (state.value.id != null) {
                        useCases.deleteAccount(
                            Account(
                                id = state.value.id,
                                name = state.value.name,
                                amount = state.value.amount ?: 0
                            )
                        )
                    }
                }
            }
        }
    }

    private fun loadAccountById(accountId: Int) {
        viewModelScope.launch {
            val account = useCases.getAccount(accountId)
            if (account != null) {
                _state.value = state.value.copy(
                    id = account.id,
                    name = account.name,
                    amount = account.amount
                )
            }
        }
    }
}

