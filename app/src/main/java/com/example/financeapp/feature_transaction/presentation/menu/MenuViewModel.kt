package com.example.financeapp.feature_transaction.presentation.menu

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.feature_transaction.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val useCases: UseCases
):ViewModel() {

    private val _state = mutableStateOf(MenuState())
    val state: State<MenuState> = _state

    private var getTransactionsJob: Job? = null
    private var getAccountsJob: Job? = null

    init {
        getTransactions(1)
        getAccounts()
    }

    fun onEvent(event: MenuEvents) {
        when(event) {
            is MenuEvents.ChangeAccount -> getTransactions(event.account.id!!)
        }
    }

    private fun getTransactions(accountId: Int) {
        getTransactionsJob?.cancel()
        getTransactionsJob = useCases.getAllTransactions(accountId)
            .onEach { transactions ->
                val lastFiveTransactions = if (transactions.size >= 5) {
                    transactions.take(5)
                } else {
                    transactions
                }
                _state.value = state.value.copy(
                    transactions = lastFiveTransactions
                )
            }.launchIn(viewModelScope)
    }
    private fun getAccounts() {
        getAccountsJob?.cancel()
        getAccountsJob = useCases.getAllAccounts()
            .onEach { accounts ->
                _state.value = state.value.copy(
                    accounts = accounts
                )
            }.launchIn(viewModelScope)
    }
}

