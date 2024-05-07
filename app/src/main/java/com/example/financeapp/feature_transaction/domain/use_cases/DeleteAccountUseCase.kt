package com.example.financeapp.feature_transaction.domain.use_cases

import com.example.financeapp.feature_transaction.domain.model.Account
import com.example.financeapp.feature_transaction.domain.repository.Repository
import kotlinx.coroutines.flow.firstOrNull

class DeleteAccountUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(account: Account) {
        val transactions = repository.getAllTransactions(account.id!!).firstOrNull()
            ?: listOf()

        transactions.onEach {transaction ->
            repository.deleteTransaction(transaction)
        }
        repository.deleteAccount(account)
    }
}
