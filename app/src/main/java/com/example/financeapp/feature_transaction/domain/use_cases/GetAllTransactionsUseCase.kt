package com.example.financeapp.feature_transaction.domain.use_cases

import com.example.financeapp.feature_transaction.domain.model.Transaction
import com.example.financeapp.feature_transaction.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllTransactionsUseCase(
    private val repository: Repository
) {
    operator fun invoke(accountId: Int): Flow<List<Transaction>> {
        return repository.getAllTransactions(accountId).map {transactions ->
            transactions.sortedByDescending { it.dateTime }
        }
    }
}
