package com.example.financeapp.feature_transaction.domain.use_cases

import com.example.financeapp.feature_transaction.domain.model.Account
import com.example.financeapp.feature_transaction.domain.repository.Repository

class AddAccountUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(account: Account) {
        repository.insertAccount(account)
    }
}
