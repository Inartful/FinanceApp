package com.example.financeapp.feature_transaction.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
//                useCases.addAccount(
//                    Account(
//                        id = 3,
//                        amount = 0,
//                        name = "General"
//                    )
//                )
//                useCases.addAccount(
//                    Account(
//                        id = 4,
//                        amount = 0,
//                        name = "Saving"
//                    )
//                )
//                useCases.addAccount(
//                    Account(
//                        amount = 0,
//                        name = "For scroll"
//                    )
//                )

//                repeat(10) {it ->
//                    useCases.deleteAccount(
//                        Account(
//                            id = it,
//                            amount = 0,
//                            name = "Saving"
//                        )
//                    )
//                }
            } catch (e: Exception) {
                Log.e("ex", e.message!!)
            }
        }
    }
}