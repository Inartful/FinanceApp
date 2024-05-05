package com.example.financeapp.feature_transaction.presentation.add_transaction

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.feature_transaction.domain.model.InvalidTransactionException
import com.example.financeapp.feature_transaction.domain.model.Transaction
import com.example.financeapp.feature_transaction.domain.use_cases.UseCases
import com.example.financeapp.feature_transaction.domain.util.CategoryType
import com.example.financeapp.feature_transaction.domain.util.TransactionType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class AddTransactionViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {

    private val _state = mutableStateOf(AddTransactionState())
    val state: State<AddTransactionState> = _state

    private var getAccountsJob: Job? = null

    init {
        getAccounts()
    }

    fun onEvent(event: AddTransactionEvents) {
        try {
            when(event) {
                is AddTransactionEvents.ChangeName -> {
                    _state.value = state.value.copy(name = event.name)
                }

                is AddTransactionEvents.ChangeType -> {
                    _state.value = state.value.copy(
                        type = event.type,
                        category = if (state.value.type == TransactionType.Expense) {
                            CategoryType.Income.Salary
                        } else {
                            CategoryType.Expense.House
                        }
                    )
                }

                is AddTransactionEvents.ChangeCategory -> {
                    _state.value = state.value.copy(category = event.category)
                }

                is AddTransactionEvents.ChangeAccount -> {
                    _state.value = state.value.copy(accountId = event.accountId)
                }

                is AddTransactionEvents.ViewCategory -> {
                    _state.value = state.value.copy(
                        categoryVisible = !state.value.categoryVisible
                    )
                }

                is AddTransactionEvents.ViewAccount -> {
                    _state.value = state.value.copy(
                        accountVisible = !state.value.accountVisible
                    )
                }

                is AddTransactionEvents.ChangeAmount -> {
                    if (event.amount in 1..9999999) {
                        _state.value = state.value.copy(amount = event.amount)
                    } else {
                        TODO()
                    }
                }

                is AddTransactionEvents.LoadTransaction ->
                    loadTransactionById(transactionId = event.id)

                is AddTransactionEvents.SaveTransaction -> {
                    viewModelScope.launch {
                        try {
                            useCases.addTransaction(
                                Transaction(
                                    dateTime = state.value.dateTime ?: LocalDateTime.now(),
                                    type = state.value.type,
                                    category = state.value.category,
                                    name = state.value.name,
                                    amount = state.value.amount,
                                    accountId = state.value.accountId
                                )
                            )
                        } catch (e: InvalidTransactionException) {
                            Log.e("error", e.message ?: "Error")
                        }
                    }
                }
            }
        } catch (e: InvalidTransactionException) {
                Log.e("error", e.message ?: "Error")
            }
    }

    private fun loadTransactionById(transactionId: Int) {
        viewModelScope.launch {
            val transaction = useCases.getTransaction(transactionId)
            if (transaction != null) {
                _state.value = state.value.copy(
                    accountId = transaction.accountId,
                    type = transaction.type,
                    amount = transaction.amount,
                    category = transaction.category,
                    name = transaction.name ?: "",
                    dateTime = transaction.dateTime
                    )
            }
        }
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