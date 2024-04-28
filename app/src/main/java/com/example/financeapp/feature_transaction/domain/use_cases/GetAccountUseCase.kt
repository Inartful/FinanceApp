package com.example.financeapp.feature_transaction.domain.use_cases

import android.util.Log
import com.example.financeapp.feature_transaction.domain.model.Account
import com.example.financeapp.feature_transaction.domain.repository.Repository

class GetAccountUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(id: Int): Account? {
        Log.i("test","get account")
        return repository.getAccountById(id)
    }
}
