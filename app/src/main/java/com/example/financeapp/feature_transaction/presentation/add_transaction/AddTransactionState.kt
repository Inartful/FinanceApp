package com.example.financeapp.feature_transaction.presentation.add_transaction

import com.example.financeapp.feature_transaction.domain.util.CategoryType
import com.example.financeapp.feature_transaction.domain.util.TransactionType
import java.time.LocalDateTime

data class AddTransactionState(
    val type: TransactionType = TransactionType.Expense,
    val category: CategoryType = CategoryType.Expense.House,
    val name: String = "",
    val amount: Int = 0,
    val accountId: Int = -1,
    val dateTime: LocalDateTime? = null
)