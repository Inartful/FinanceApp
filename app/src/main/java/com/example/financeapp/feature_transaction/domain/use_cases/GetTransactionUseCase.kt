package com.example.financeapp.feature_transaction.domain.use_cases

import com.example.financeapp.feature_transaction.domain.repository.TransactionRepository

class GetTransactionUseCase(
    private val repository: TransactionRepository
) {
    suspend operator fun invoke(id: Int) {
        repository.getTransactionById(id)
    }
}
