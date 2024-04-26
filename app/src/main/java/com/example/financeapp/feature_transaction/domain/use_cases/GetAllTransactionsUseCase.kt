package com.example.financeapp.feature_transaction.domain.use_cases

import com.example.financeapp.feature_transaction.domain.model.Transaction
import com.example.financeapp.feature_transaction.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllTransactionsUseCase(
    private val repository: TransactionRepository
) {
    suspend operator fun invoke(): Flow<List<Transaction>> {
        return repository.getAllTransactions().map {transactions ->
            transactions.sortedByDescending { it.dateTime }
        }
    }
}
