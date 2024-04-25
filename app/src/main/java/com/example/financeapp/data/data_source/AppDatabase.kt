package com.example.financeapp.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.financeapp.domain.model.Transactions
import com.example.financeapp.domain.util.Converters

@Database(
    entities = [Transactions::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase:RoomDatabase() {
    abstract val transactionDAO: TransactionDAO

    companion object {
        const val DATABASE_NAME = "app_db"
    }
}