package com.example.financeapp.feature_transaction.domain.use_cases

import com.example.financeapp.feature_transaction.domain.model.Account
import com.example.financeapp.feature_transaction.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetAllAccountUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<List<Account>> {
        return repository.getAllAccounts()
    }
}