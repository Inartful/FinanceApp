package com.example.financeapp.feature_transaction.domain.use_cases

data class TransactionUseCases(
    val addTransactionUseCase: AddTransactionUseCase,
    val deleteTransactionUseCase: DeleteTransactionUseCase,
    val getAllTransactionsUseCase: GetAllTransactionsUseCase,
    val getTransactionUseCase: GetTransactionUseCase
)
