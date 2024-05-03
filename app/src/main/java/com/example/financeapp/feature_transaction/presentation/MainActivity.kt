package com.example.financeapp.feature_transaction.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.financeapp.feature_transaction.presentation.add_transaction.AddTransactionScreen
import com.example.financeapp.feature_transaction.presentation.menu.MenuScreen
import com.example.financeapp.feature_transaction.presentation.util.Screen
import com.example.financeapp.ui.theme.FinanceAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
//        viewModel.performTransaction()
        setContent {
            FinanceAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.MainScreen.route
                    ) {
                        composable(route = Screen.MainScreen.route) {
                            MenuScreen(navController = navController)
                        }
                        composable(
                            route = Screen.AddTransaction.route + "?id={id}",
                            arguments = listOf(
                                navArgument("id") {
                                    type = NavType.IntType
                                    defaultValue = -1
                                    nullable = false
                                }
                            )
                        ) { entry ->
                            AddTransactionScreen(
                                navController = navController,
                                transactionId = entry.arguments?.getInt("id") ?: -1
                            )
                        }
                    }
                }
            }
        }
    }
}
