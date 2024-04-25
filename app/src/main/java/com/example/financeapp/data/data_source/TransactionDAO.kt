package com.example.financeapp.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.financeapp.domain.model.Transactions
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDAO {

    @Query("SELECT * FROM transactions")
    fun getAllTransactions(): Flow<List<Transactions>>

    @Query("SELECT * FROM transactions WHERE id = :id")
    suspend fun getTransactionById(id: Int): Transactions?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(note: Transactions)

    @Delete
    suspend fun deleteTransaction(note: Transactions)

}