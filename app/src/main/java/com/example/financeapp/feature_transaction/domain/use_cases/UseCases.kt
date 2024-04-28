package com.example.financeapp.feature_transaction.domain.use_cases

data class UseCases(
    val addTransaction: AddTransactionUseCase,
    val deleteTransaction: DeleteTransactionUseCase,
    val getAllTransactions: GetAllTransactionsUseCase,
    val getTransaction: GetTransactionUseCase,

    val addAccount: AddAccountUseCase,
    val deleteAccount: DeleteAccountUseCase,
    val getAllAccount: GetAllAccountUseCase,
    val getAccount: GetAccountUseCase,
)
