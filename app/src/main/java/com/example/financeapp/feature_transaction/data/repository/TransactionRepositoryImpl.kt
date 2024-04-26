package com.example.financeapp.feature_transaction.data.repository

import com.example.financeapp.feature_transaction.data.data_source.TransactionDAO
import com.example.financeapp.feature_transaction.domain.model.Transaction
import com.example.financeapp.feature_transaction.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow

class TransactionRepositoryImpl(
    private val dao: TransactionDAO
): TransactionRepository {
    override fun getAllTransactions(): Flow<List<Transaction>> {
        return dao.getAllTransactions()
    }

    override suspend fun getTransactionById(id: Int): Transaction? {
        return dao.getTransactionById(id)
    }

    override suspend fun insertTransaction(transaction: Transaction) {
        return dao.insertTransaction(transaction)
    }

    override suspend fun deleteTransaction(transaction: Transaction) {
        return dao.deleteTransaction(transaction)
    }
}