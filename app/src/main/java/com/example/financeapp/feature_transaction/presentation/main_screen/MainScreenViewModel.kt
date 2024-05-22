package com.example.financeapp.feature_transaction.presentation.main_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.feature_transaction.domain.model.Transaction
import com.example.financeapp.feature_transaction.domain.use_cases.UseCases
import com.example.financeapp.feature_transaction.domain.util.TransactionType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val useCases: UseCases
):ViewModel() {

    private val _state = mutableStateOf(MainScreenState())
    val state: State<MainScreenState> = _state

    private var getTransactionsJob: Job? = null
    private var getAccountsJob: Job? = null

    init {
        getAccountsAndTransactions()
    }

    fun onEvent(event: MainScreenEvents) {
        when (event) {
            is MainScreenEvents.ChangeAccount -> getTransactions(event.account.id!!)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun getAccountsAndTransactions() {
        getAccountsJob?.cancel()
        getAccountsJob = useCases.getAllAccounts()
            .flatMapMerge { accounts ->
                _state.value = state.value.copy(
                    accounts = accounts
                )
                val firstAccountId = accounts.firstOrNull()?.id
                if (firstAccountId != null) {
                    _state.value = state.value.copy(
                        accountId = firstAccountId
                    )
                    useCases.getAllTransactions(firstAccountId)
                } else {
                    flowOf(emptyList())
                }
            }
            .onEach { transactions ->
                getAllExpenses(transactions)
                val lastFiveTransactions = if (transactions.size >= 5) {
                    transactions.take(5)
                } else {
                    transactions
                }
                _state.value = state.value.copy(transactions = lastFiveTransactions)
            }
            .launchIn(viewModelScope)
    }

    private fun getTransactions(accountId: Int) {
        _state.value = state.value.copy(
            accountId = accountId
        )
        getTransactionsJob?.cancel()
        getTransactionsJob = useCases.getAllTransactions(accountId)
            .onEach { transactions ->
                getAllExpenses(transactions)
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

    private fun getAllExpenses(transactions: List<Transaction>) {
        _state.value = state.value.copy(
            expenses = 0
        )
        transactions.onEach { transaction ->
            if(transaction.type == TransactionType.Expense
                && (transaction.dateTime.dayOfMonth == LocalDateTime.now().dayOfMonth
                        && transaction.dateTime.month == LocalDateTime.now().month)) {
                val lastExpenses = state.value.expenses
                _state.value = state.value.copy(
                    expenses = lastExpenses + transaction.amount
                )
            }
        }
    }
}