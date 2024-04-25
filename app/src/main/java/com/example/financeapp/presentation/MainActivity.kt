package com.example.financeapp.presentation

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room.databaseBuilder
import com.example.financeapp.data.data_source.AppDatabase
import com.example.financeapp.domain.model.Transactions
import com.example.financeapp.domain.util.TransactionType
import com.example.financeapp.ui.theme.FinanceAppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var db = databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "transactions-db"
        ).build()

        val transaction = Transactions(
            dateTime = LocalDateTime.now(),
            type = TransactionType.Income,
            amount = 100
        )

        GlobalScope.launch(Dispatchers.Main) {
            db.transactionDAO.insertTransaction(transaction)
            val transactions = db.transactionDAO.getAllTransactions()
            updateUI(transactions)
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FinanceAppTheme {
        Greeting("Android")
    }
}

private suspend fun updateUI(transactions: Flow<List<Transactions>>) {
    transactions.collect { transaction ->
        Log.i("trans", "${transaction}" )
    }
}