package com.example.financeapp.di

import android.app.Application
import androidx.room.Room
import com.example.financeapp.feature_transaction.data.data_source.AppDatabase
import com.example.financeapp.feature_transaction.data.repository.RepositoryImpl
import com.example.financeapp.feature_transaction.domain.repository.Repository
import com.example.financeapp.feature_transaction.domain.use_cases.AddAccountUseCase
import com.example.financeapp.feature_transaction.domain.use_cases.AddTransactionUseCase
import com.example.financeapp.feature_transaction.domain.use_cases.DeleteAccountUseCase
import com.example.financeapp.feature_transaction.domain.use_cases.DeleteTransactionUseCase
import com.example.financeapp.feature_transaction.domain.use_cases.GetAccountUseCase
import com.example.financeapp.feature_transaction.domain.use_cases.GetAllAccountUseCase
import com.example.financeapp.feature_transaction.domain.use_cases.GetAllTransactionsUseCase
import com.example.financeapp.feature_transaction.domain.use_cases.GetTransactionUseCase
import com.example.financeapp.feature_transaction.domain.use_cases.UseCases
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
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideTransactionRepository(db: AppDatabase): Repository {
        return RepositoryImpl(db.DAO)
    }

    @Provides
    @Singleton
    fun provideTransactionUseCases(repository: Repository): UseCases {
        return UseCases(
            getAllTransactions = GetAllTransactionsUseCase(repository),
            deleteTransaction = DeleteTransactionUseCase(repository),
            addTransaction = AddTransactionUseCase(repository),
            getTransaction = GetTransactionUseCase(repository),
            getAccount = GetAccountUseCase(repository),
            deleteAccount = DeleteAccountUseCase(repository),
            addAccount = AddAccountUseCase(repository),
            getAllAccounts = GetAllAccountUseCase(repository)
        )
    }

//    @Provides
//    @Singleton
//    fun provideSettingsViewModel(): SettingsViewModel {
//        return SettingsViewModel()
//    }
}
