package com.example.financeapp.feature_transaction.presentation.add_transaction

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.feature_transaction.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTransactionViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {

    private val _state = mutableStateOf(AddTransactionState())
    val state: State<AddTransactionState> = _state

    fun onEvent(event: AddTransactionEvents) {
        when(event) {
            is AddTransactionEvents.ChangeName -> {
                _state.value = state.value.copy(name = event.name)
            }
            is AddTransactionEvents.ChangeType -> {
                _state.value = state.value.copy(type = event.type)
            }

            is AddTransactionEvents.ChangeCategory -> {
                _state.value = state.value.copy(category = event.category)
            }
        }
    }

    fun loadTransactionById(transactionId: Int) {
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
}