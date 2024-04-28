package com.example.financeapp.feature_transaction.domain.repository


import com.example.financeapp.feature_transaction.domain.model.Account
import com.example.financeapp.feature_transaction.domain.model.Transaction
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getAllTransactions(): Flow<List<Transaction>>
    fun getAllAccounts(): Flow<List<Account>>
    suspend fun getTransactionById(id: Int): Transaction?
    suspend fun getAccountById(id: Int): Account?
    suspend fun insertTransaction(transaction: Transaction)
    suspend fun insertAccount(account: Account)
    suspend fun deleteTransaction(transaction: Transaction)
    suspend fun deleteAccount(account: Account)
}