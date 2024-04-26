package com.example.financeapp.feature_transaction.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.financeapp.feature_transaction.data.data_source.TransactionDAO
import com.example.financeapp.feature_transaction.domain.model.Transaction
import com.example.financeapp.feature_transaction.domain.repository.TransactionRepository
import com.example.financeapp.feature_transaction.domain.use_cases.TransactionUseCases
import com.example.financeapp.feature_transaction.domain.util.CurrencyType
import com.example.financeapp.feature_transaction.domain.util.TransactionType
import com.example.financeapp.ui.theme.FinanceAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.Clock
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Currency
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var useCases: TransactionUseCases
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GlobalScope.launch(Dispatchers.Main) {
//            useCases.addTransactionUseCase(Transaction(
//                dateTime = LocalDateTime.now(),
//                type = TransactionType.Income,
//                amount = 400,
//                currency = CurrencyType.KZT
//                ))
            val transactions = useCases.getAllTransactionsUseCase()
            transactions.collect {
                for (transaction in it) {
                    Log.i("trans", "${transaction}" )
                }
            }
        }

        setContent {
            FinanceAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello ${LocalDateTime.now().month}!",
        modifier = modifier
    )
}