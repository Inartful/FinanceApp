package com.example.financeapp.feature_transaction.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.feature_transaction.domain.model.Account
import com.example.financeapp.feature_transaction.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {
    fun performTransaction() {

        viewModelScope.launch {
            try {
                useCases.addAccount(
                    Account(
                        id = 3,
                        amount = 0,
                        name = "For scroll"
                    )
                )
//                useCases.addTransaction(
//                    Transaction(
//                        dateTime = LocalDateTime.now(),
//                        type = TransactionType.Income,
//                        amount = 600,
//                        accountId = 1,
//                        category = CategoryType.Income.Salary,
//                        name = "Salary"
//                    )
//                )
            } catch (e: Exception) {
                Log.e("ex", e.message!!)
            }
        }
    }
}