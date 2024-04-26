package com.example.financeapp.di

import android.app.Application
import androidx.room.Room
import com.example.financeapp.feature_transaction.data.data_source.AppDatabase
import com.example.financeapp.feature_transaction.data.data_source.TransactionDAO
import com.example.financeapp.feature_transaction.data.repository.TransactionRepositoryImpl
import com.example.financeapp.feature_transaction.domain.repository.TransactionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideTransactionRepository(db: AppDatabase): TransactionRepository {
        return TransactionRepositoryImpl(db.transactionDAO)
    }
}
