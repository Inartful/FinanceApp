package com.example.financeapp.feature_transaction.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.financeapp.feature_transaction.domain.model.Account
import com.example.financeapp.feature_transaction.domain.model.Transaction
import com.example.financeapp.feature_transaction.domain.util.Converters

@Database(
    entities = [Transaction::class, Account::class],
    version = 9
)
@TypeConverters(Converters::class)
abstract class AppDatabase:RoomDatabase() {
    abstract val DAO: Dao
    companion object {
        const val DATABASE_NAME = "app_db"
    }
}