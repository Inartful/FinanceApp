package com.example.financeapp.feature_transaction.presentation.add_account

data class AddAccountState(
    val name: String = "",
    val id: Int? = null,
    val amount: Int? = null
)