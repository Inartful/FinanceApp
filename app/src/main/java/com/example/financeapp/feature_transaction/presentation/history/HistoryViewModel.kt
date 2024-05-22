package com.example.financeapp.feature_transaction.presentation.history

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
class HistoryViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {

    private val _state = mutableStateOf(HistoryState())
    val state: State<HistoryState> = _state

    var getTransactionsJob: Job? = null

    fun onEvent(event: HistoryEvents) {
        when(event) {
            is HistoryEvents.LoadTransactions -> getTransactions(event.accountId)
        }
    }
    private fun getTransactions(accountId: Int) {
        getTransactionsJob?.cancel()
        getTransactionsJob = useCases.getAllTransactions(accountId)
            .onEach { transactions ->
                _state.value = state.value.copy(
                    transactions = transactions
                )
            }.launchIn(viewModelScope)
    }
}
