package com.example.financeapp.feature_transaction.presentation.add_account

sealed class AddAccountEvents{
    data class ChangeName(val name: String): AddAccountEvents()
    data class LoadAccount(val accountId: Int): AddAccountEvents()
    data class ChangeAmount(val amount: Int?): AddAccountEvents()
    object SaveAccount: AddAccountEvents()
    object DeleteAccount: AddAccountEvents()
}