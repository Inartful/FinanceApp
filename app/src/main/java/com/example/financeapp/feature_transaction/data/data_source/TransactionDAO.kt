package com.example.financeapp.feature_transaction.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.financeapp.feature_transaction.domain.model.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDAO {

    @Query("SELECT * FROM `transaction`")
    fun getAllTransactions(): Flow<List<Transaction>>

    @Query("SELECT * FROM `transaction` WHERE id = :id")
    suspend fun getTransactionById(id: Int): Transaction?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: Transaction)

    @Delete
    suspend fun deleteTransaction(transaction: Transaction)

}