package com.example.financeapp.feature_transaction.presentation.add_transaction

import com.example.financeapp.feature_transaction.domain.util.CategoryType
import com.example.financeapp.feature_transaction.domain.util.TransactionType

sealed class AddTransactionEvents {
    data class ChangeName(val name: String): AddTransactionEvents()
    data class ChangeType(val type: TransactionType): AddTransactionEvents()
    data class ChangeCategory(val category: CategoryType): AddTransactionEvents()
}