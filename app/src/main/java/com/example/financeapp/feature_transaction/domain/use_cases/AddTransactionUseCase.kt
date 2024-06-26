package com.example.financeapp.feature_transaction.domain.use_cases

import com.example.financeapp.feature_transaction.domain.model.InvalidTransactionException
import com.example.financeapp.feature_transaction.domain.model.Transaction
import com.example.financeapp.feature_transaction.domain.repository.Repository
import com.example.financeapp.feature_transaction.domain.util.TransactionType
import kotlinx.coroutines.flow.firstOrNull

class AddTransactionUseCase(
    private val repository: Repository
) {

    @Throws(InvalidTransactionException::class)
    suspend operator fun invoke(transaction: Transaction) {
        val accounts = repository.getAllAccounts().firstOrNull()
            ?: throw InvalidTransactionException("No accounts found")
        val originalAccount = accounts.find { it.id == transaction.accountId }
            ?: throw InvalidTransactionException("Account not found")

        if (transaction.amount <= 0) {
            throw InvalidTransactionException("Transaction amount can not be 0 or negative")
        }
        when (transaction.type) {
            TransactionType.Income ->
                repository.insertAccount(
                    originalAccount.copy(
                        amount = originalAccount.amount + transaction.amount
                    )
                )
            TransactionType.Expense ->
                if (originalAccount.amount < transaction.amount) {
                    throw InvalidTransactionException("Transaction amount can not be " +
                            "lower then account amount")
                } else {
                    repository.insertAccount(
                        originalAccount.copy(
                            amount = originalAccount.amount - transaction.amount
                        )
                    )
                }
        }
        repository.insertTransaction(transaction)
    }
}
