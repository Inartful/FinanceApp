package com.example.financeapp.feature_transaction.domain.use_cases

import com.example.financeapp.feature_transaction.domain.model.InvalidTransactionException
import com.example.financeapp.feature_transaction.domain.model.Transaction
import com.example.financeapp.feature_transaction.domain.repository.Repository
import com.example.financeapp.feature_transaction.domain.util.TransactionType
import kotlinx.coroutines.flow.firstOrNull

class DeleteTransactionUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(transaction: Transaction) {
        val accounts = repository.getAllAccounts().firstOrNull()
            ?: throw InvalidTransactionException("No accounts found")
        val originalAccount = accounts.find { it.id == transaction.accountId }
            ?: throw InvalidTransactionException("Account not found")
        val transactions = repository.getAllTransactions(transaction.accountId).firstOrNull()
            ?: listOf()
        val originalTransaction = transactions.find { it.id == transaction.id }

        if (originalTransaction != null) {
            when (transaction.type) {
                TransactionType.Expense -> {
                    repository.insertAccount(
                        originalAccount.copy(
                            amount = originalAccount.amount + originalTransaction.amount
                        )
                    )
                }
                TransactionType.Income ->
                    if (originalAccount.amount < transaction.amount) {
                        throw InvalidTransactionException("Transaction amount can not be " +
                                "lower then account amount")
                    } else {
                        repository.insertAccount(
                            originalAccount.copy(
                                amount = originalAccount.amount - originalTransaction.amount
                            )
                        )
                    }
            }
            repository.deleteTransaction(originalTransaction)
        }
    }
}
