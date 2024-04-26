package com.example.financeapp.feature_transaction.domain.use_cases

import com.example.financeapp.feature_transaction.domain.model.InvalidTransactionException
import com.example.financeapp.feature_transaction.domain.model.Transaction
import com.example.financeapp.feature_transaction.domain.repository.TransactionRepository

class AddTransactionUseCase(
    private val repository: TransactionRepository
) {
    @Throws(InvalidTransactionException::class)
    suspend operator fun invoke(transaction: Transaction) {
        if(transaction.amount <= 0 ) {
            throw InvalidTransactionException("The amount can not be negative or zero")
        }
        repository.insertTransaction(transaction)
    }
}
