package com.example.financeapp.feature_transaction.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.feature_transaction.domain.model.Account
import com.example.financeapp.feature_transaction.domain.model.Transaction
import com.example.financeapp.feature_transaction.domain.use_cases.UseCases
import com.example.financeapp.feature_transaction.domain.util.CurrencyType
import com.example.financeapp.feature_transaction.domain.util.TransactionType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {
    fun performTransaction() {

        viewModelScope.launch {
            try {
//                useCases.addAccount(
//                    Account(
//                        id = 1,
//                        amount = 0,
//                        name = "Current"
//                    )
//                )
                useCases.addTransaction(
                    Transaction(
                        dateTime = LocalDateTime.now(),
                        type = TransactionType.Income,
                        amount = 600,
                        accountId = 1
                    )
                )
                val transactions = useCases.getAllTransactions()
                val account = useCases.getAccount(1)
                Log.i("trans","$account")
                transactions.collect { transactionList ->
                    transactionList.forEach { transaction ->
                        Log.i("trans", "Transaction: $transaction")
                    }
                }
            } catch (e: Exception) {
                Log.e("ex", e.message!!)
            }
        }
    }
}