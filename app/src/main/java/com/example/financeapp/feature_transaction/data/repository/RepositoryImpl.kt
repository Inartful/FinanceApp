package com.example.financeapp.feature_transaction.data.repository

import com.example.financeapp.feature_transaction.data.data_source.Dao
import com.example.financeapp.feature_transaction.domain.model.Account
import com.example.financeapp.feature_transaction.domain.model.Transaction
import com.example.financeapp.feature_transaction.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(
    private val dao: Dao
): Repository {
    override fun getAllTransactions(): Flow<List<Transaction>> {
        return dao.getAllTransactions()
    }
    override fun getAllAccounts(): Flow<List<Account>> {
        return dao.getAllAccounts()
    }

    override suspend fun getTransactionById(id: Int): Transaction? {
        return dao.getTransactionById(id)
    }
    override suspend fun getAccountById(id: Int): Account? {
        return dao.getAccountById(id)
    }

    override suspend fun insertTransaction(transaction: Transaction) {
        return dao.insertTransaction(transaction)
    }
    override suspend fun insertAccount(account: Account) {
        return dao.insertAccount(account)
    }

    override suspend fun deleteTransaction(transaction: Transaction) {
        return dao.deleteTransaction(transaction)
    }
    override suspend fun deleteAccount(account: Account) {
        return dao.deleteAccount(account)
    }
}